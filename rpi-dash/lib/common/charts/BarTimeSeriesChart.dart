import 'package:charts_flutter/flutter.dart' as charts;
import 'package:flutter/material.dart';

import '../LocalizedTimeFactory.dart';

class BarTimeSeriesChart extends StatelessWidget {
  final List<charts.Series<MonthSeries, DateTime>> seriesList;

  BarTimeSeriesChart(this.seriesList);

  factory BarTimeSeriesChart.by(List<MonthSeries> data) {
    return new BarTimeSeriesChart(_fetchData(data));
  }

  @override
  Widget build(BuildContext context) {
    return new charts.TimeSeriesChart(
      seriesList,
      animate: true,
      animationDuration: Duration(milliseconds: 1000),
      dateTimeFactory: LocalizedTimeFactory(Localizations.localeOf(context)),
      defaultRenderer: new charts.BarRendererConfig<DateTime>(),
      defaultInteractions: false,
      behaviors: [new charts.SelectNearest(), new charts.DomainHighlighter()],
    );
  }

  /// Create one series with sample hard coded data.
  static List<charts.Series<MonthSeries, DateTime>> _fetchData(
      List<MonthSeries> _data) {
    return [
      new charts.Series<MonthSeries, DateTime>(
        id: 'Month',
        colorFn: (_, __) => charts.MaterialPalette.blue.shadeDefault,
        domainFn: (MonthSeries item, _) => item.date,
        measureFn: (MonthSeries item, _) => item.count,
        data: _data,
      )
    ];
  }
}

/// Модель.
class MonthSeries {
  final DateTime date;
  final int count;

  MonthSeries(this.date, this.count);
}
