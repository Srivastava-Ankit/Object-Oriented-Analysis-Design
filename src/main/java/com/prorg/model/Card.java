package com.prorg.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "card")
public class Card {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    @NotNull
    @Size(min = 1, max = 255)
    private String title;

    @Column
    @Size(max = 1023)
    private String description;

    @ManyToOne
    @JoinColumn(name = "swimlane_id", nullable = false)
    private Swimlane swimlane;

    @ManyToMany(mappedBy = "cards")
    @LazyCollection(value = LazyCollectionOption.FALSE)
    private List<User> assignedUsers;

    public int getId() {
        return id;
    }
    public Card setId(int id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }
    public Card setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }
    public Card setDescription(String description) {
        this.description = description;
        return this;
    }

    public Swimlane getSwimlane() {
        return swimlane;
    }
    public Card setSwimlane(Swimlane swimlane) {
        this.swimlane = swimlane;
        return this;
    }

    public List<User> getAssignedUsers() {
        return assignedUsers;
    }
    public Card setAssignedUsers(List<User> assignedUser) {
        this.assignedUsers = assignedUser;
        return this;
    }
}
