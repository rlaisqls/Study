package com.example.practice;

import org.kohsuke.github.GHIssue;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Practice2 {
    @Value("token")
    private static String token;

    private void printParticipants(int eventId) throws IOException {
        // Get GitHub issue to check homework
        GHIssue issue = getGhIssue(eventId);

        // Get participants
        Set<String> participants = getUsernames(issue);

        // Print participants
        printParticipant(participants);
    }

    private void printReviewers() throws IOException {
        // Get GitHub issue to check homework
        GHIssue issue = getGhIssue(30);

        // Get reviewers
        Set<String> reviewers = getUsernames(issue);

        // Print reviewers
        printParticipant(reviewers);
    }

    private GHIssue getGhIssue(int eventId) throws IOException {
        GitHub gitHub = new GitHubBuilder().withOAuthToken(token).build();
        GHRepository repository = gitHub.getRepository("whiteship/live-study");
        return repository.getIssue(eventId);
    }

    private Set<String> getUsernames(GHIssue issue) throws IOException {
        Set<String> participants = new HashSet<>();
        issue.getComments().forEach(c -> participants.add(c.getUserName()));
        return participants;
    }

    private void printParticipant(Set<String> participants) {
        participants.forEach(System.out::println);
    }

    public static void main(String[] args) throws IOException {
        Practice2 studyDashboard = new Practice2();
        studyDashboard.printReviewers();
        studyDashboard.printParticipants(15);

    }
}