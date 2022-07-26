package com.example.beige;

public class SongCollection {

    private Song songs[] = new Song[2];
    public SongCollection() {
        Song glimpseOfUs = new Song("glimpse_of_us",
                "1. Glimpse Of Us",
                "Joji",
                "https://p.scdn.co/mp3-preview/071c22f355ed0d03fdc176dcb25a487f5ffb661c?cid=2afe87a64b0042dabf51f37318616965",
                3.89,
                R.drawable.joji_glimpse_of_us);

        Song feelSpecial = new Song("feel_special",
                "2. Feel Special",
                "TWICE",
                "https://p.scdn.co/mp3-preview/25e0099a6756e9faa060362211b003d72eadc655?cid=2afe87a64b0042dabf51f37318616965",
                3.45,
                R.drawable.twice);

        songs[0] = glimpseOfUs;
        songs[1] = feelSpecial;
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

    public int getNextSong(int currentSongIndex){
        if(currentSongIndex >= songs.length-1){
            return currentSongIndex = 0;
        }
        else {
            return currentSongIndex +1;
        }
    }

    public int getPrevSong(int currentSongIndex){
        if(currentSongIndex <= 0){
            return currentSongIndex;
        }
        else {
            return currentSongIndex -1;
        }
    }
}
