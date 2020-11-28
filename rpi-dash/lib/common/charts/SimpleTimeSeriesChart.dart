import 'package:charts_flutter/flutter.dart' as charts;
import 'package:flutter/material.dart';

class SimpleTimeSeriesChart extends StatelessWidget {
  final List<charts.Series> seriesList;

  SimpleTimeSeriesChart(this.seriesList);

  /// На основе даты.
  factory SimpleTimeSeriesChart.byDate(DateTime dateTime) {
    return new SimpleTimeSeriesChart(
      fetchData(dateTime),
    );
  }

  @override
  Widget build(BuildContext context) {
    return charts.TimeSeriesChart(
      seriesList,
      animate: true,
      dateTimeFactory: const charts.LocalDateTimeFactory(),
    );
  }

  /// Create one series with sample hard coded data.
  static List<charts.Series<TimeSeries, DateTime>> fetchData(
      DateTime dateTime) {
    //TODO запрос данных с сервера
    final data = [
      new TimeSeries(new DateTime(2015), 30),
      new TimeSeries(new DateTime(2016), 15),
      new TimeSeries(new DateTime(2017), 120),
      new TimeSeries(new DateTime(2018), 80),
      new TimeSeries(new DateTime(2019), 150),
      new TimeSeries(new DateTime(2020), 280),
      new TimeSeries(new DateTime(2021), 0),
    ];

    return [
      new charts.Series<TimeSeries, DateTime>(
        id: 'Динамика появления новых заявок',
        colorFn: (_, __) => charts.MaterialPalette.blue.shadeDefault,
        domainFn: (TimeSeries series, _) => series.time,
        measureFn: (TimeSeries series, _) => series.count,
        data: data,
      )
    ];
  }
}

/// Модель графика
class TimeSeries {
  final DateTime time;
  final int count;

  TimeSeries(this.time, this.count);
}
