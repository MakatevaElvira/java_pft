package ru.stqu.pft.github;

import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;

public class GitHubTests {

  @Test
  public void testCommits() throws IOException {
    Github github = new RtGithub("c1c798d6c8422b0466e7efc62e0800a1b562a261");
    RepoCommits commits = github.repos().get(new Coordinates.Simple("MakatevaElvira", "java_pft")).commits();
    for (RepoCommit commit : commits.iterate(new ImmutableMap.Builder<String,String>().build())) { //построили пустой набор пар,чтобы получить все!
      System.out.println(new RepoCommit.Smart(commit).message());
    }

  }
}
