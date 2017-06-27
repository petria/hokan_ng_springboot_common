package org.freakz.hokan_ng_springboot.bot.common.models;

import java.io.Serializable;

/**
 * User: petria
 * Date: Jul 15, 2009
 * Time: 8:29:17 AM
 */
public class HoroHolder implements Serializable {

    private int horoscope;
    private String horoscopeText;

    public HoroHolder(int horoscope, String horoscopeText) {
        horoscope = horoscope;
        horoscopeText = horoscopeText;
    }

    public int getHoroscope() {
        return horoscope;
    }

    public String getHoroscopeText() {
        return horoscopeText;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
/*        sb.append(HORO_NAMES[getHoroscope()]);
        sb.append(" (");
        sb.append(HORO_DATES[getHoroscope()]);
        sb.append("): ");*/
        sb.append(getHoroscopeText());
        return sb.toString();
    }

}
