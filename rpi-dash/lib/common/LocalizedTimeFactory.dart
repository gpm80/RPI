import 'package:charts_flutter/flutter.dart' as charts;
import 'package:flutter/material.dart';
import 'package:intl/intl.dart';

/// Локализация даты
class LocalizedTimeFactory implements charts.DateTimeFactory {
  final Locale locale;

  const LocalizedTimeFactory(this.locale);

  DateTime createDateTimeFromMilliSecondsSinceEpoch(
      int millisecondsSinceEpoch) {
    return DateTime.fromMillisecondsSinceEpoch(millisecondsSinceEpoch);
  }

  DateTime createDateTime(int year,
      [int month = 1,
      int day = 1,
      int hour = 0,
      int minute = 0,
      int second = 0,
      int millisecond = 0,
      int microsecond = 0]) {
    return DateTime(
        year, month, day, hour, minute, second, millisecond, microsecond);
  }

  /// Returns a [DateFormat].
  DateFormat createDateFormat(String pattern) {
    return DateFormat(pattern, locale.languageCode);
  }
}
