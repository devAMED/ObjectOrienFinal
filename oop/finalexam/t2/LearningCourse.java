package oop.finalexam.t2;

/** A single university learning course. */
public class LearningCourse {

    private String title;
    private String prerequisites;
    private String topics;

    public LearningCourse(String title, String prerequisites, String topics) {
        this.title = title;
        this.prerequisites = prerequisites;
        this.topics = topics;
    }

    /* Getters */
    public String getTitle()         { return title; }
    public String getPrerequisites() { return prerequisites; }
    public String getTopics()        { return topics; }

    /* Setters */
    public void setTitle(String title)               { this.title = title; }
    public void setPrerequisites(String prerequisites){ this.prerequisites = prerequisites; }
    public void setTopics(String topics)             { this.topics = topics; }

    /** Prints: Title  –  Prereq: ...  –  Topics: ... */
    @Override public String toString() {
        return title + "  –  Prereq: " + prerequisites + "  –  Topics: " + topics;
    }
}
