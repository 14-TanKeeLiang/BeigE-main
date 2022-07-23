package com.example.beige;

public class SongCollection {

    private Song songs[] = new Song[2];
    public SongCollection() {
        Song theWayYouLookTonight = new Song("s1001",
                "1. The Way You Look Tonight",
                "Michael Buble",
                "https://p.scdn.co/mp3-preview/a5b8972e764025020625bbf9c1c2bbb06e394a60?cid=2afe87a64b0042dabf51f37318616965",
                4.66,
                R.drawable.michael_buble_collection);

        Song billieJean = new Song("s1002",
                "2. Billie Jean",
                "Michael Jackson",
                "https://p.scdn.co/mp3-preview/71638a1eac196a5daa9fbf152693585e323d8558?cid=2afe87a64b0042dabf51f37318616965",
                4.90,
                R.drawable.billie_jean);

        songs[0] = theWayYouLookTonight;
        songs[1] = billieJean;
    }

    public int searchSongById(String id){

        for(int index = 0; index < songs.length; index++){
            Song tempSong = songs[index];
            if(tempSong.getId().equals(id)){
                return index;
            }
        }
        return -1;
    }

    public Song getCurrentSong(int currentSongId){
        return songs[currentSongId];
    }
}
