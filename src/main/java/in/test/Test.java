package in.test;

import org.jetbrains.annotations.NotNull;

import java.util.*;

public class Test {
    public static void main(String[] args) {
        MostUpvoted votingTracker = new MostUpVotedImpl();
        votingTracker.upvote(7);
        votingTracker.upvote(7);
        votingTracker.upvote(8);
        System.out.println(votingTracker.mostUpvoted());        // returns 7
        votingTracker.upvote(8);
        votingTracker.upvote(8);
        System.out.println(votingTracker.mostUpvoted());        // returns 8
        votingTracker.downvote(8);
        votingTracker.downvote(8);
        System.out.println(votingTracker.mostUpvoted());        // returns 7
        votingTracker.downvote(7);
        votingTracker.downvote(7);
        votingTracker.downvote(8);
        System.out.println(votingTracker.mostUpvoted());        // returns -1 since there is no question ID with votes greater than 0
    }
}

interface MostUpvoted {
    Integer mostUpvoted();
    void upvote(Integer questionId);
    void downvote(Integer questionId);
}

class MostUpVotedImpl implements  MostUpvoted{
    class Question implements Comparable<Question>{
        int questionId;
        int votes; // agg of up and down

        public Question(int qId, int vote){
            this.questionId = qId;
            this.votes = vote;
        }

        public void addVote(int vote){
            this.votes += vote;
        }

        @Override
        public String toString() {
            return "Question{" +
                    "questionId=" + questionId +
                    ", votes=" + votes +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Question question = (Question) o;
            return questionId == question.questionId;
        }

        @Override
        public int hashCode() {
            return Objects.hash(questionId);
        }


        @Override
        public int compareTo(@NotNull Question o) {
            if(this.votes == o.votes)
                return this.questionId - o.questionId;
            return o.votes - this.votes;
        }
    }


    private Map<Integer, Question> questionMap = new HashMap<>();

    private TreeSet<Question> sortedmap = new TreeSet<>(new Comparator<Question>() {
        @Override
        public int compare(Question o1, Question o2) {
            return o1.compareTo(o2);
        }
    }); // question lookup
//    private PriorityQueue<Question> heap = new PriorityQueue<>(); // maintains the question order on votes


//    T = O(1)
    @Override
    public Integer mostUpvoted() {
        if(sortedmap.size() == 0) return -1;
        return sortedmap.first().questionId;
    }

    // T = 2 * O(n)
    @Override
    public void upvote(Integer questionId) {
        vote(questionId, 1);
    }

    @Override
    public void downvote(Integer questionId) {
        vote(questionId, -1);
    }

    private void vote(int questionId, int vote){
        Question q = null;
        if(questionMap.containsKey(questionId))
            q = questionMap.get(questionId);
        else {
            q = new Question(questionId, 0);
            questionMap.put(questionId, q);
        }
        sortedmap.remove(q);
        q.addVote(vote);
        if(q.votes > 0)
            sortedmap.add(q);
    }
}




        /*
        * votingTracker.upvote(7);
votingTracker.upvote(7);
votingTracker.upvote(8);
votingTracker.mostUpvoted();        // returns 7
votingTracker.upvote(8);
votingTracker.upvote(8);
votingTracker.mostUpvoted();        // returns 8
votingTracker.downvote(8);
votingTracker.downvote(8);
votingTracker.mostUpvoted();        // returns 7
votingTracker.downvote(7);
votingTracker.downvote(7);
votingTracker.downvote(8);
votingTracker.mostUpvoted();        // returns -1 since there is no question ID with votes greater than 0
*
*
*
* q:7 5:4 = 1
* q:8 3:2 = 1
*
*
*
* approch: heap
*   Questions{
*       int vote;
*   }
*
*
* approch: 2 DoublyLinkedList & Map
*
*   q#8#2,  1#5#1
*
        * */