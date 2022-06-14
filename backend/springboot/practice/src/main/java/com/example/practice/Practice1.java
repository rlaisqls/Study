package com.example.practice;

import org.kohsuke.github.*;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Practice1 {
    @Value("token")
    private static String token;

    private Set<StudyReview> studyReviews  = new HashSet<>();

    private Set<String> reviews = new HashSet<>();

    private void loadReviews() throws IOException {
        GitHub gitHub = new GitHubBuilder().withOAuthToken(token).build();
        GHRepository repository = gitHub.getRepository("whiteship/live-study");
        GHIssue issue = repository.getIssue(30);

        List<GHIssueComment> reviews = issue.getComments();
        for (GHIssueComment review : reviews) {
            studyReviews.add(new StudyReview(review.getUserName(),review.getBody()));
        }
    }

    public Set<StudyReview> getStudyReviews() {
        return studyReviews;
    }

    public static void main(String[] args) throws IOException {
        Practice1 studyDashboard = new Practice1();
        studyDashboard.loadReviews();
        studyDashboard.getStudyReviews().forEach(System.out::println);
    }


    public record StudyReview(String reviewer, String review) {
    }
}