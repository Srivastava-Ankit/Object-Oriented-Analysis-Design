package com.prorg.helper;

import org.springframework.beans.factory.annotation.Autowired;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Random;

public class Password {
    private static final int ITERATIONS = 64000;
    private static final int KEY_LENGTH = 256;
    private static final int SALT_BYTE_SIZE = 24;
    private Random random;

    @Autowired
    public Password(Random random) {
        this.random = random;
    }

    private byte[] fromBase64(String hex)
    {
        return DatatypeConverter.parseBase64Binary(hex);
    }

    private String toBase64(byte[] array)
    {
        return DatatypeConverter.printBase64Binary(array);
    }

    public String getNextSalt() {
        byte[] salt = new byte[SALT_BYTE_SIZE];
        random.nextBytes(salt);
        return toBase64(salt);
    }

    public String hash(String password, String salt) {
        char[] passwordArray = password.toCharArray();
        byte[] saltArray = fromBase64(salt);
        PBEKeySpec spec = new PBEKeySpec(passwordArray, saltArray, ITERATIONS, KEY_LENGTH);
        Arrays.fill(passwordArray, Character.MIN_VALUE);
        try {

            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            return toBase64(skf.generateSecret(spec).getEncoded());
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new AssertionError("Error while hashing a password: " + e.getMessage(), e);
        } finally {
            spec.clearPassword();
        }
    }

    public boolean isExpectedPassword(String password, String salt, String expectedHash) {
        byte[] pwdHash = fromBase64(hash(password, salt));
        byte[] expectedHashArray = fromBase64(expectedHash);
        Arrays.fill(password.toCharArray(), Character.MIN_VALUE);
        if (pwdHash.length != expectedHashArray.length) return false;
        for (int i = 0; i < pwdHash.length; i++) {
            if (pwdHash[i] != expectedHashArray[i]) return false;
        }
        return true;
    }
}
