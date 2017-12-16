package com.prorg.model;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;


@Entity
@Table(name = "storyboard_details")
public class Storyboard {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    @Size(max = 255, min = 1)
    private String title;

    @Column(name = "description")
    @Size(max = 1000)
    private String description;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;

    @OneToMany(mappedBy = "storyboard")
    @LazyCollection(value = LazyCollectionOption.FALSE)
    private List<Swimlane> swimlanes;

    @ManyToMany(mappedBy = "accessibleStoryboards")
    @LazyCollection(value = LazyCollectionOption.FALSE)
    private List<User> usersWhoHaveAccess;

    public int getId() {
        return id;
    }
    public Storyboard setId(int id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }
    public Storyboard setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }
    public Storyboard setDescription(String description) {
        this.description = description;
        return this;
    }

    public User getCreatedBy() {
        return createdBy;
    }
    public Storyboard setCreatedBy(User userId) {
        this.createdBy = userId;
        return this;
    }

    public List<Swimlane> getSwimlanes() {
        return swimlanes;
    }
    public Storyboard setSwimlanes(List<Swimlane> swimlanes) {
        this.swimlanes = swimlanes;
        return this;
    }

    public List<User> getUsersWhoHaveAccess() {
        return usersWhoHaveAccess;
    }
    public Storyboard setUsersWhoHaveAccess(List<User> usersWhoHaveAccess) {
        this.usersWhoHaveAccess = usersWhoHaveAccess;
        return this;
    }
}