package com.example.beige;

import java.util.ArrayList;

public class SongCollection {

    private static Song[] songs = new Song[10];

    static ArrayList<Song> searchList = new ArrayList<Song>();

    public SongCollection() {
        Song glimpseOfUs = new Song("joji",
                "Glimpse Of Us",
                "Joji",
                "https://p.scdn.co/mp3-preview/071c22f355ed0d03fdc176dcb25a487f5ffb661c?cid=2afe87a64b0042dabf51f37318616965",
                3.89,
                R.drawable.joji_glimpse_of_us);

        Song feelSpecial = new Song("twice",
                "Feel Special",
                "TWICE",
                "https://p.scdn.co/mp3-preview/25e0099a6756e9faa060362211b003d72eadc655?cid=2afe87a64b0042dabf51f37318616965",
                3.45,
                R.drawable.twice);

        Song familyTies = new Song("baby_keem",
                "family ties(with Kendrick Lamar)",
                "Baby Keem",
                "https://p.scdn.co/mp3-preview/b850748651cf920a41a9eab900f08144e41310d6?cid=2afe87a64b0042dabf51f37318616965",
                4.2,
                R.drawable.baby_keem);

        Song feels = new Song("calvin_harris",
                "Feels(ft. Pharrell Williams, Katy Perry & Big Sean)",
                "Calvin Harris",
                "https://p.scdn.co/mp3-preview/abc3107d3b24c6e4aedd08a96d34ae4497ee4fc1?cid=2afe87a64b0042dabf51f37318616965",
                3.72,
                R.drawable.calvin_harris);

        Song richMinion = new Song("yeat",
                "Rich Minion",
                "Yeat",
                "https://p.scdn.co/mp3-preview/f99ee22e524f18ca5afbd80c95309fcbbbafb5f8?cid=2afe87a64b0042dabf51f37318616965",
                2.76,
                R.drawable.yeat);

        Song xOTourLif3 = new Song("lil_uzi_vert",
                "XO Tour Lif3",
                "Lil Uzi Vert",
                "https://p.scdn.co/mp3-preview/8b62a61d6199c9db24ac7507caa90ee707c89fce?cid=2afe87a64b0042dabf51f37318616965",
                3.05,
                R.drawable.lil_uzi_vert);

        Song thatsWhatILike = new Song("bruno_mars",
                "That's What I Like",
                "Bruno Mars",
                "https://p.scdn.co/mp3-preview/dff97adc200a31774425d79cec92ad0e117ce2be?cid=2afe87a64b0042dabf51f37318616965",
                3.44,
                R.drawable.bruno_mars);

        Song stay = new Song("the_kid_loaroi",
                "Stay",
                "The Kid LOAROI",
                "https://p.scdn.co/mp3-preview/40a8daf1f3cbb5c6363e68de859ba4af3377344c?cid=2afe87a64b0042dabf51f37318616965",
                2.36,
                R.drawable.the_kid_laroi);

        Song intoTheNight = new Song("yoasobi",
                "夜に駆ける",
                "YOASOBI",
                "https://p.scdn.co/mp3-preview/5ec6d2efbf3a412f1c2a4a8b728bb2ea0653fdb1?cid=2afe87a64b0042dabf51f37318616965",
                4.37,
                R.drawable.yoru_ni_kakeru);

        Song highestInTheRoom = new Song("travis_scott",
                "HIGHEST IN THE ROOM",
                "Travis Scott",
                "https://p.scdn.co/mp3-preview/7a31c51e5690dc881150a189e0c29c0d18206729?cid=2afe87a64b0042dabf51f37318616965",
                2.93,
                R.drawable.travis_scott);

        songs[0] = glimpseOfUs;
        songs[1] = feelSpecial;
        songs[2] = intoTheNight;
        songs[3] = familyTies;
        songs[4] = feels;
        songs[5] = xOTourLif3;
        songs[6] = thatsWhatILike;
        songs[7] = stay;
        songs[8] = richMinion;
        songs[9] = highestInTheRoom;
    }

    public static void getSearchList() {
        searchList.add(songs[0]);
        searchList.add(songs[1]);
        searchList.add(songs[2]);
        searchList.add(songs[3]);
        searchList.add(songs[4]);
        searchList.add(songs[5]);
        searchList.add(songs[6]);
        searchList.add(songs[7]);
        searchList.add(songs[8]);
        searchList.add(songs[9]);
    }

    public static void getSearchListRemove() {
        searchList.remove(songs[0]);
        searchList.remove(songs[1]);
        searchList.remove(songs[2]);
        searchList.remove(songs[3]);
        searchList.remove(songs[4]);
        searchList.remove(songs[5]);
        searchList.remove(songs[6]);
        searchList.remove(songs[7]);
        searchList.remove(songs[8]);
        searchList.remove(songs[9]);
    }

    public int searchSongById(String id){

        for(int index = 0; index < songs.length; index++){
            Song tempSong = songs[index];
            if(tempSong.getId().equals(id)){
                return index;
            }
        }
        return 0;
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
