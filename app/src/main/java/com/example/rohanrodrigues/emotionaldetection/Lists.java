package com.example.rohanrodrigues.emotionaldetection;

public class Lists {
    public static void main(String[] args){

    }

    public String response(String input) {
        String possess, adject, object, positivev, negativev, neutralv;
        String response = "";
        String[] poss = { "Your ", "Your mom's ", "Your dad's ", "Your friend's ", "Your sister's ", "Your brother's ",
                "Your dog's ", "Your Cat's ", "Your Goldfish's ", "Your lover's " };
        String[] adj = { "cool ", "hot ", "smelly ", "small ", "huge ", "dirty ", "dry ", "bright pink ",
                "transparent ", "wet " };
        String[] obj = { "house ", "dog ", "cat ", "stick ", "friend ", "balloon ", "foot ", "ball ", "phone ",
                "umbilical cord " };
        String[] negv = { "got burnt.", "died.", "left.", "is now deceased.", "was cremated.", "was shot.",
                "was attacked by a gorilla.", "was killed by a duck.", "was eaten by an elephant.", "got boiled" };
        String[] neutv = { "is fine.", "isn't dead.", "ate lunch.", "has a neutral mood.", "likes to watch movies.",
                "owns 2 pairs of shoes.", "is not a refrigerator.", "is probably not a murderer.",
                "didn't rob a bank." };
        String[] posv = { "found a dollar.", "won a contest.", "defeated the evil overlord.", "built a time machine.",
                "became president of the universe.", "is happy.", "fixed your problem.", "is great.",
                "is alive and well.", "won a game." };

        if (input.equals("POSITIVE")) {
            possess = poss[(int) (Math.random() * 10)];
            adject = adj[(int) (Math.random() * 10)];
            object = obj[(int) (Math.random() * 10)];
            positivev = posv[(int) (Math.random() * 10)];
            response = possess + adject + object + positivev;

        }
        if (input.equals("NEGATIVE")) {
            possess = poss[(int) (Math.random() * 10)];
            adject = adj[(int) (Math.random() * 10)];
            object = obj[(int) (Math.random() * 10)];
            negativev = negv[(int) (Math.random() * 10)];
            response = possess + adject + object + negativev;

        }
        if (input.equals("NEUTRAL")) {
            possess = poss[(int) (Math.random() * 10)];
            adject = adj[(int) (Math.random() * 10)];
            object = obj[(int) (Math.random() * 10)];
            neutralv = neutv[(int) (Math.random() * 10)];
            response = possess + adject + object + neutralv;

        }
        return response;
    }
}