package com.san.ds.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TeamRankingByVotes_1366 {

    static class Team implements Comparable<Team>{
        char teamId;
        int[] vote;

        int teamSize;

        public Team(char team, int[] vote, int teamSize){
            this.teamId = team;
            this.vote = vote;
            this.teamSize = teamSize;
        }

        @Override
        public int compareTo(Team o) {
            for(int rank = 0; rank < teamSize; rank++){
                if(this.vote[rank] != o.vote[rank] )
                    return this.vote[rank] - o.vote[rank];
            }
            return Character.compare(teamId, o.teamId);
        }
    }

    public String rankTeams(String[] votes) {
        Map<Character, Integer> teamIdMap = new HashMap<>();
        int id = 0;
        for(char team : votes[0].toCharArray()){
            teamIdMap.put(team, id);
            id++;
        }
        int totalTeam = teamIdMap.size();

        int[][] rankCount = new int[totalTeam][totalTeam];

        for(String rankVote : votes){
            for(int rank = 0; rank < totalTeam; rank++)
                rankCount[teamIdMap.get(rankVote.charAt(rank))][rank] += 1;
        }
        for(int[] a : rankCount)
            System.out.println(Arrays.toString(a));
        Map<Character, Team> finalRankMap = new HashMap<>();

        teamIdMap.forEach((key, value) -> finalRankMap.put(key, new Team(key, rankCount[value], totalTeam)));

        StringBuilder sb = new StringBuilder();
        finalRankMap.values().stream().sorted().forEach(team -> sb.append(team.teamId));

        return sb.toString();
    }

    public static void main(String[] args) {
        String[] a = {"BCA","CAB","CBA","ABC","ACB","BAC"};
        System.out.println(new TeamRankingByVotes_1366().rankTeams(a));
    }
}
