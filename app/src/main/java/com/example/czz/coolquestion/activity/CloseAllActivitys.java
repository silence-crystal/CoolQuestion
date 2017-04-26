package com.example.czz.coolquestion.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;

public class CloseAllActivitys {
	public static List<Activity> list = new ArrayList<Activity>();
	public static void closeAllActivity() {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i)!=null) {
				list.get(i).finish();
			}
		}
	}
}
