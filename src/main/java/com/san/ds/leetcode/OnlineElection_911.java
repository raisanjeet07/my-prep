package com.san.ds.leetcode;

import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;

public class OnlineElection_911 {

    private int[] dp;
    private int[] times;


    TreeMap<Integer, Integer> timeLine;


    private void initV2(int[] persons, int[] times){
        TreeMap<Integer, Integer> timeLine = new TreeMap<>();
        Map<Integer, Integer> voteCount = new HashMap<>();
        int i =0;
        int lastWinner = persons[i];
        timeLine.put(times[i], persons[i]);
        int person;
        for(; i< persons.length; i++){
            person = persons[i];
            voteCount.put(person, voteCount.getOrDefault(person, 0) + 1);
            timeLine.put(times[i], lastWinner == person ? lastWinner : voteCount.get(person) >= voteCount.get(lastWinner) ? person : lastWinner);
        }
        this.timeLine = timeLine;
    }

    public int q(int t) {
        return timeLine.floorKey(t);
    }

    private void init(int[] persons, int[] times){
        this.times = times;
        int[] dp = new int[persons.length];
        this.dp = dp;
        DynamicSorting ds = new DynamicSorting();
        int i = 0;
//        Set<Integer> set = new HashSet<>();
        for(int person : persons){
//            set.add(person);
            ds.addVote(person);
            dp[i++] = ds.getLeadingPerson();
        }
//        System.out.println(set.stream().sorted().collect(Collectors.toList()));
//        ds.traverse();
    }

    class DynamicSorting{

        Map<Integer, Node> map = new HashMap<>();
        Node head;
        Node tail;

        int size = 0;
        private class Node {
            int person;
            int votes;
            Node next;
            Node prv;
             public Node(int person){
                 this.person = person;
                 this.votes = 1;
             }

            @Override
            public String toString() {
                return "Node{" +
                        "person=" + person +
                        ", votes=" + votes +
                        '}';
            }
        }

        public void addVote(int person){
            if(map.containsKey(person)){
                Node personNode = map.get(person);
                personNode.votes = personNode.votes + 1;
                while (personNode.prv != null && personNode.prv.votes <= personNode.votes)
                    bubbleUp(personNode);
            }else{
                Node newPersonNode = new Node(person);
                map.put(person, newPersonNode);
                if(head == null){
                    head = newPersonNode;
                    tail = newPersonNode;
                }else{
                    newPersonNode.prv = tail;
                    tail.next = newPersonNode;
                    tail = newPersonNode;
                    while (newPersonNode.prv != null && newPersonNode.prv.votes <= newPersonNode.votes)
                        bubbleUp(newPersonNode);
                }
                size++;
            }
        }

        public int getLeadingPerson(){
            return head.person;
        }

        public void traverse(){
            Node tmp = head;
            while(tmp != null){
                System.out.println(tmp);
                tmp = tmp.next;
            }
        }

        private void bubbleUp(Node currentNode){
            if(currentNode.next == null && currentNode.prv == null)
                return;

            // if head node
            if(currentNode == head)
                return;


            // its last node:

            if(currentNode == tail){
                tail = currentNode.prv;
                tail.next = null;

                currentNode.next = tail;
                currentNode.prv = tail.prv;

                if(tail.prv != null)
                    tail.prv.next = currentNode;
                tail.prv = currentNode;
                if(currentNode.prv == null)
                    head = currentNode;
            }else{
                // middle node
                Node prv = currentNode.prv;
                Node next = currentNode.next;

                prv.next = next;
                next.prv = prv;

                currentNode.next = prv;
                currentNode.prv = prv.prv;

                if(prv.prv != null){
                    prv.prv.next = currentNode;
                }else{
                    head = currentNode;
                }
                prv.prv = currentNode;
            }

        }

    }


    public int q2(int t) {
        int i = 0;
        if(t < times[0])
            return dp[0];
        for(; i < times.length; i++){
            if(times[i] == t)
                break;
            if(times[i] > t){
                i--;
                break;
            }
        }
        return i == times.length ? dp[i-1] : dp[i];
    }

    public static void main(String[] args) {
//        int[] persons = {0,0,0,1,1,2,2,2,3,4,1,3,5,5,3,6,5,4,6,7,7,4,7,6,7,3,1,8,4,9,0,10,5,2,10,8,10,9,10,6,8,9,8,9,11,11,11,11,12,12,12,12,6,2,5,7,13,9,0,12,10,8,11,1,4,3,13,13,13,13,12,6,3,9,1,10,14,2,0,4,13,11,7,8,5,14,14,14,14,14,9,8,14,13,10,5,12,6,2,1};
//        int[] times = {352,401,485,646,652,944,1287,1292,1318,1337,1347,1398,1454,1502,1559,1709,1786,2245,2255,2677,2787,2886,2918,3202,3314,3457,3475,3560,3602,3740,3816,3829,3841,3842,3930,3943,4049,4056,4206,4292,4367,4457,4576,4586,4632,4637,4710,4915,5104,5153,5204,5223,5475,5503,5531,5600,5685,5692,5813,5909,5939,6186,6370,6457,6675,6680,6720,6734,6754,6985,7102,7106,7121,7142,7405,7440,7517,7590,7863,8017,8083,8088,8164,8176,8303,8477,8511,8665,8691,8823,9115,9123,9260,9446,9450,9629,9636,9703,9868,9997};
        int[] persons = {0,1,2,2,1};
        int[] times = {20,28,29,54,56};
        OnlineElection_911 obj = new OnlineElection_911();
        obj.init(persons, times);
        System.out.println("Initialization done......");
//        int[] queries = {2902,7516,574,9050,6980,4647,5122,2464,7702,9390,8182,1316,9636,5503,1518,7321,5654,2828,6780,7472,4597,9333,5687,3675,9691,5699,3544,7465,4725,6088,8771,7682,6404,5936,566,1201,1442,2689,5446,5818,4432,698,7740,7519,5299,4945,1683,3924,4389,7243,4986,5607,3804,8827,3952,2899,5795,4057,5954,4052,1313,4256,1373,7557,4114,2498,7400,3223,4687,3678,3192,6797,4419,9627,2569,9157,3108,9622,7728,5693,6356,5439,1390,1558,1284,1185,2883,5094,7979,1548,1021,3237,5165,6316,3163,6937,3840,9466,3682,4896,7847,5972,1612,7931,7826,5839,2099,7615,1712,6112,7966,9719,5325,1535,755,8469,6511,9176,993,5714,1643,7306,6444,4283,388,1526,6823,3806,1449,4034,1240,7575,8507,7474,1902,9883,2385,5406,1784,3890,1521,5906,4600,2515,4817,1571,5914,4976,8851,7382,9194,9714,7284,4832,4921,9259,3619,5567,6589,5172,9540,7324,1868,7989,7577,1507,447,5731,6230,9086,7766,2195,3407,3284,8424,8074,1496,4443,7108,9410,8544,1497,2512,6825,8066,721,8368,2764,4049,2744,6162,4143,5857,2219,9893,3720,2614,2707,8528,3545};
//        int output[] = {4,10,0,14,3,9,11,5,2,14,8,2,12,2,1,9,7,5,3,10,9,14,7,4,12,9,1,10,9,10,5,2,11,12,0,1,1,5,12,0,6,1,2,10,12,11,3,2,6,9,11,7,4,14,2,4,9,2,10,2,2,10,1,10,2,5,9,6,9,4,7,3,6,10,5,8,7,10,2,9,8,12,1,1,1,1,5,11,0,1,1,6,11,8,7,3,0,10,4,9,2,10,3,0,2,0,5,2,3,10,0,6,12,1,1,5,1,8,1,9,3,9,11,10,0,1,3,4,1,2,1,10,5,10,5,2,5,12,3,2,1,0,9,5,9,3,12,11,14,9,8,6,9,9,11,8,4,5,1,11,10,9,5,0,10,1,0,9,8,14,2,5,7,6,5,4,1,6,6,14,5,1,5,3,4,1,5,5,2,5,10,2,0,5,2,4,5,5,5,1};
        int[] queries = {28,53,57,29,29,28,30,20,56,55};
        int[] output = {1,2,1,2,2,1,2,0,1,2};
        for(int i = 0; i < queries.length; i++){
            int query = queries[i];
            int param_1 = obj.q2(query);
            if(param_1 != output[i])
                System.out.println(param_1 +" : "+ query +" : "+ output[i]);
        }

    }
    /*

    [[[0,0,0,1,1,2,2,2,3,4,1,3,5,5,3,6,5,4,6,7,7,4,7,6,7,3,1,8,4,9,0,10,5,2,10,8,10,9,10,6,8,9,8,9,11,11,11,11,12,12,12,12,6,2,5,7,13,9,0,12,10,8,11,1,4,3,13,13,13,13,12,6,3,9,1,10,14,2,0,4,13,11,7,8,5,14,14,14,14,14,9,8,14,13,10,5,12,6,2,1,352,401,485,646,652,944,1287,1292,1318,1337,1347,1398,1454,1502,1559,1709,1786,2245,2255,2677,2787,2886,2918,3202,3314,3457,3475,3560,3602,3740,3816,3829,3841,3842,3930,3943,4049,4056,4206,4292,4367,4457,4576,4586,4632,4637,4710,4915,5104,5153,5204,5223,5475,5503,5531,5600,5685,5692,5813,5909,5939,6186,6370,6457,6675,6680,6720,6734,6754,6985,7102,7106,7121,7142,7405,7440,7517,7590,7863,8017,8083,8088,8164,8176,8303,8477,8511,8665,8691,8823,9115,9123,9260,9446,9450,9629,9636,9703,9868,9997],2902,7516,574,9050,6980,4647,5122,2464,7702,9390,8182,1316,9636,5503,1518,7321,5654,2828,6780,7472,4597,9333,5687,3675,9691,5699,3544,7465,4725,6088,8771,7682,6404,5936,566,1201,1442,2689,5446,5818,4432,698,7740,7519,5299,4945,1683,3924,4389,7243,4986,5607,3804,8827,3952,2899,5795,4057,5954,4052,1313,4256,1373,7557,4114,2498,7400,3223,4687,3678,3192,6797,4419,9627,2569,9157,3108,9622,7728,5693,6356,5439,1390,1558,1284,1185,2883,5094,7979,1548,1021,3237,5165,6316,3163,6937,3840,9466,3682,4896,7847,5972,1612,7931,7826,5839,2099,7615,1712,6112,7966,9719,5325,1535,755,8469,6511,9176,993,5714,1643,7306,6444,4283,388,1526,6823,3806,1449,4034,1240,7575,8507,7474,1902,9883,2385,5406,1784,3890,1521,5906,4600,2515,4817,1571,5914,4976,8851,7382,9194,9714,7284,4832,4921,9259,3619,5567,6589,5172,9540,7324,1868,7989,7577,1507,447,5731,6230,9086,7766,2195,3407,3284,8424,8074,1496,4443,7108,9410,8544,1497,2512,6825,8066,721,8368,2764,4049,2744,6162,4143,5857,2219,9893,3720,2614,2707,8528,3545]]

    * */
}
