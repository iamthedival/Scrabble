import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by div on 10/20/2016.
 */
public class scabbleGame {

    String[] myWords = new String[80000];
    int totalWords  = 0;
    int tilesUsed = 0;
    Bag tileBag = new Bag(null);
    Tile[] tileValues = new Tile[26];
    Hand optionHand = new Hand();
    Hand enteredHand =  new Hand();
    Player player = new Player("",0);
    TopTen bestPlayers = new TopTen();

    public scabbleGame(){
        try {
            tileBag.generateTiles();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println("bag generation failed");
            e.printStackTrace();
        }

        tileBag.shuffle();
        optionHand.addTile(tileBag.myBagOfTiles[1]);
        optionHand.addTile(tileBag.myBagOfTiles[2]);
        optionHand.addTile(tileBag.myBagOfTiles[3]);
        optionHand.addTile(tileBag.myBagOfTiles[4]);
        optionHand.addTile(tileBag.myBagOfTiles[5]);
        optionHand.addTile(tileBag.myBagOfTiles[6]);
        optionHand.addTile(tileBag.myBagOfTiles[7]);

        tilesUsed = 7;

        /*
        try{
            bestPlayers.openTopTen();
        }catch (IOException e){
            System.out.println("Failed");
        }

        bestPlayers.sort();
        */
    }


    public void getWords() throws IOException{
        Scanner check =  new Scanner(System.in);

        Scanner reader = new Scanner(new File("words.txt"));

        String strLine = "";
        int wordPos = 0;
        int i = 0;
        int count = 0;
        while(reader.hasNext()){

            strLine = reader.next();
                wordPos++;
                myWords[wordPos] = new String(strLine);

        }
        reader.close();



        totalWords = wordPos;
    }

    public void openTilePoints() throws IOException{

        Scanner reader = new Scanner(new File("scrabblevalues.txt"));

        String Letter = "";
        int bagPos = 0;
        int i = 0;
        int count = 0;

        while(reader.hasNext()){

            Letter = reader.next();
            int Points = reader.nextInt();
            int Frequency = reader.nextInt();
            count++;

            //	System.out.println(bagPos);
            tileValues[count] = new Tile(Letter, Points);
        }
        reader.close();

        //System.out.println();
    }

    public int getTilePoints(Tile tile){
        int pointValue = 0;
        for(int I = 0; I<=26; I++){
            if(tile.myLetter.equals(tileValues[I].getMyLetter())){  //FIXME:Nullpointer exception
                pointValue = tileValues[I].getMyPoints();
            }
        }
        return pointValue;
    }

    public void grabTiles(){

    }

    public void replaceTiles(){
        for(int i = 1; i <= 7 ;i++){
            if(optionHand.getTile(i).visibility == false){
                tilesUsed++;
                optionHand.setTile(tileBag.myBagOfTiles[tilesUsed],i);
            }
        }
    }

    public void enterOptionTile(Tile tile){
        enteredHand.addTile(tile);
    }



    int scoreWord() throws IOException{
        int score = 0;
        int i = 0;
        if(this.checkWord(this.enteredHand.getWord())) { //FIXME: test wont pass
            for (i = 1; i <= enteredHand.getWord().length(); i++) {
                System.out.println(i);
                score = score + enteredHand.getTile(i).getMyPoints();
            }
        }
        return score;
    }

    public int checkHand(){
        int check = 1;
        int flag = 0;

        return check;
    }

    public boolean checkWord(String word) {
        Boolean exists = false;
        String enteredWord = enteredHand.getWord();
        for (int i = 0; i < totalWords; i++ ){
            if(enteredWord.equals(this.myWords[i])) {
                exists = true;
                break;
            }
        }
        return exists;
    }

}
