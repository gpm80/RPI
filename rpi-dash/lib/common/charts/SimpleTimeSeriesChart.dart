import 'package:charts_flutter/flutter.dart' as charts;
import 'package:flutter/material.dart';

class SimpleTimeSeriesChart extends StatelessWidget {
  final List<charts.Series> seriesList;

  SimpleTimeSeriesChart(this.seriesList);

  /// На основе даты.
  factory SimpleTimeSeriesChart.by(List<TimeSeries> data) {
    return new SimpleTimeSeriesChart(
      fetchData(data),
    );
  }

  @override
  Widget build(BuildContext context) {
    return charts.TimeSeriesChart(
      seriesList,
      animate: true,
      animationDuration: Duration(milliseconds: 1000),
      dateTimeFactory: const charts.LocalDateTimeFactory(),
    );
  }

  /// Create one series with sample hard coded data.
  static List<charts.Series<TimeSeries, DateTime>> fetchData(
      List<TimeSeries> _data) {
    return [
      new charts.Series<TimeSeries, DateTime>(
        id: 'Динамика появления новых заявок',
        colorFn: (_, __) => charts.MaterialPalette.blue.shadeDefault,
        domainFn: (TimeSeries series, _) => series.time,
        measureFn: (TimeSeries series, _) => series.count,
        data: _data,
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
