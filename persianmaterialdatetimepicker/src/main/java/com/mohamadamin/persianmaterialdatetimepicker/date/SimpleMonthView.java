/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mohamadamin.persianmaterialdatetimepicker.date;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;

import com.mohamadamin.persianmaterialdatetimepicker.TypefaceHelper;
import com.mohamadamin.persianmaterialdatetimepicker.utils.LanguageUtils;

import java.util.Locale;

public class SimpleMonthView extends MonthView {
  DatePickerController controller;
  private Paint rectPaint = new Paint();

  public SimpleMonthView(Context context, AttributeSet attr, DatePickerController controller) {
    super(context, attr, controller);
    this.controller = controller;
  }

  @Override
  public void drawMonthDay(Canvas canvas, int year, int month, int day, int x, int y, int startX, int stopX, int startY, int stopY) {
    if (mSelectedDay == day) {
      canvas.drawCircle(x, y - (MINI_DAY_NUMBER_TEXT_SIZE / 3), DAY_SELECTED_CIRCLE_SIZE,
        mSelectedCirclePaint);
    }
    if (isHighlighted(year, month, day)) {
      Typeface typefaceBold = Typeface.create(TypefaceHelper.get(getContext(), controller.getTypeface()), Typeface.BOLD);
      mMonthNumPaint.setTypeface(typefaceBold);
    } else {
      Typeface typefaceNormal = Typeface.create(TypefaceHelper.get(getContext(), controller.getTypeface()), Typeface.NORMAL);
      mMonthNumPaint.setTypeface(typefaceNormal);
    }

    // If we have a mindate or maxdate, gray out the day number if it's outside the range.
    if (isOutOfRange(year, month, day)) {
      mMonthNumPaint.setColor(mDisabledDayTextColor);
    } else if (mSelectedDay == day) {
      mMonthNumPaint.setColor(mSelectedDayTextColor);
    } else if (mHasToday && mToday == day) {
      mMonthNumPaint.setColor(mTodayNumberColor);
    } else {
      mMonthNumPaint.setColor(isHighlighted(year, month, day) ? mHighlightedDayTextColor : mDayTextColor);
    }

//    int xPos = (canvas.getWidth() / 2);
//    int yPos = (int) ((canvas.getHeight() / 2) - ((mMonthNumPaint.descent() + mMonthNumPaint.ascent()) / 2));
    //((textPaint.descent() + textPaint.ascent()) / 2) is the distance from the baseline to the center.

//    canvas.drawText("Hello", xPos, yPos, textPaint);

//    canvas.drawText(LanguageUtils.getPersianNumbers(String.format(Locale.getDefault(), "%d", day)), xPos , y, mMonthNumPaint);
/*
    Rect r = new Rect();
    canvas.getClipBounds(r);
    mMonthNumPaint.getTextBounds(LanguageUtils.getPersianNumbers(String.format(Locale.getDefault(), "%d", day)), 0,
      LanguageUtils.getPersianNumbers(String.format(Locale.getDefault(), "%d", day)).length(), r);

    int cHeight = r.height();
    int cWidth = r.width();

    float x_a = cWidth / 2f - r.width() / 2f - r.left;
    float y_a = cHeight / 2f + r.height() / 2f - r.bottom;


    rectPaint.setColor(Color.rgb(0, 0, 0));
    rectPaint.setStyle(Paint.Style.STROKE);
    rectPaint.setStrokeWidth(3f);
    r.offset((int) y, (int) x);
    canvas.drawRect(r, rectPaint);*/

    canvas.drawText(LanguageUtils.getPersianNumbers(String.format(Locale.getDefault(), "%d", day)), x, y, mMonthNumPaint);
  }
}
