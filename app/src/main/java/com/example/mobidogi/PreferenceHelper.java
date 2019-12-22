package com.example.mobidogi;

public class PreferenceHelper {

  private static boolean mLenkkeily;
  private static boolean mLuoksetulo;
  private static boolean mHairitsevakaytos;
  private static boolean mHoitotoimenpiteet;
  private static boolean mYksinolo;
  private static boolean mSendVideo;

  private PreferenceHelper() {
  }

  public static void setLenkkeily(boolean LenkkeilyPaidFor) {
    if (LenkkeilyPaidFor) {
      mLenkkeily = true;
    } else {
      mLenkkeily = false;
    }
  }

  public static void setLuoksetulo(boolean LuoksetuloPaidFor) {
    if (LuoksetuloPaidFor) {
      mLuoksetulo = true;
    } else {
      mLuoksetulo = false;
    }
  }

  public static void setHairitsevakaytos(boolean HairitsevakaytosPaidFor) {
    if (HairitsevakaytosPaidFor) {
      mHairitsevakaytos = true;
    } else {
      mHairitsevakaytos = false;
    }
  }

  public static void setHoitotoimenpiteet(boolean HoitotoimenpiteetPaidFor) {
    if (HoitotoimenpiteetPaidFor) {
      mHoitotoimenpiteet = true;
    } else {
      mHoitotoimenpiteet = false;
    }
  }

  public static void setYksinolo(boolean YksinoloPaidFor) {
    if (YksinoloPaidFor) {
      mYksinolo = true;
    } else {
      mYksinolo = false;
    }
  }

  public static void setSendVideo(boolean SendVideoPaidFor) {
    if (SendVideoPaidFor) {
      mSendVideo = true;
    } else {
      mSendVideo = false;
    }
  }
}
