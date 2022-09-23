package com.nt;

public interface SpeakingClock {

    /**
     * Convert time, in format HH:mm, into words.
     *
     * @param time
     * 			String, representing in time
     * @return String
     * 			Words representation of time
     */
    String convert(String time);
}
