import java.util.*;

class Play {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int[] score = new int[2];
        // Player
        System.out.println("Hello, is your turn");
        int[] cards = pull(2); 

        if (cards[0] == 1) {
            cards[0] = ace();
        }
        if (cards[1] == 1) {
            cards[1] = ace();
        }

        System.out.println("Here are your cards: " + cards[0] + " " + cards[1]);
        score[0] = cards[0] + cards[1];
        System.out.println("your result :" + score[0]);

        System.out.println("Hello it's my turn");
        cards = pull(2);
        System.out.println("My visible card is " + cards[0]);
        score[1] = cards[0] + cards[1];

        boolean stop = false;
        int[] card = new int[1];
        boolean lose = false;

        // Player plays
        do {
            System.out.println("Do you want to continue ? (yes/no) ");
            String answer = scan.nextLine();
            if (answer.equals("yes") || answer.equals("yes ")) {
                card = pull(1);
                if (card[0] == 1){
                    card[0] = ace();
                }
                System.out.println("Your new card :" + card[0]);
                score[0] += card[0];
                if (score[0] > 21) {
                    System.out.println("You are over 21, looser");
                    stop = true;
                    lose = true;
                } else {
                    System.out.println("Your new score is : " + score[0]);
                }
            } else {
                stop = true;
                System.out.println("Your final score is : " + score[0]);
            }
        } while (stop == false);

        if (lose == true) {
            System.out.println("Game is over ");
        } else {

            // Dealer plays
            System.out.println("My cards are " + cards[0] + " " + cards[1]);
            
            if (cards[0] == 1) {
                cards[0] = aceDealer(cards[1]);
            } else if (cards[1] == 1) {
                cards[1] = aceDealer(cards[0]);
            }

            System.out.println("My result is " + score[1]);

            while (score[1] < 17) {
                card = pull(1);
                if (card[0] == 1) {
                    card[0] = aceDealer(score[1]);
                }
                System.out.println("My new card is " + card[0]);
                score[1] += card[0];
                if (score[1] > 21) {
                    lose = true;
                    System.out.println("The score is over 21: I loose ");
                } else {
                    System.out.println("The new score is " + score[1]);
                }
            }

            if (lose == true) {
                System.out.println("You win ");
            } else {
                if (score[0] <= score[1]) {
                    System.out.println("I'm the winner ");
                } else {
                    System.out.println("You are the winner");
                }
            }

        }

    }

    public static int[] pull(int numberCards) {
        Random randomNumberGenerator = new Random();
        int[] cartes = randomNumberGenerator.ints(numberCards, 1, 14).toArray();
        for (int i = 0; i < cartes.length; i++) {
            if (cartes[i] > 10) {
                cartes[i] = 10;
            }
        }
        return cartes;
    }

    public static int ace() {
        Scanner scan = new Scanner(System.in);
        System.out.println("You have an Ace, you have to choose betwenn 1 or 11");
        int answer = scan.nextInt();
        while (answer != 1 && answer != 11) {
            System.out.println("Your choice is not possible, choose again 11 or 1");
            answer = scan.nextInt();
        }
        return answer;
    }

    public static int aceDealer(int value){
        if (value + 11 >= 17 && value + 11 < 21){
            return 11;
        } else {
            return 1;
        }
    }

}