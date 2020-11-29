import 'package:charts_flutter/flutter.dart' as charts;
import 'package:flutter/material.dart';

class PieStatisticsChart extends StatelessWidget {
  final List<charts.Series> seriesList;

  PieStatisticsChart(this.seriesList);

  factory PieStatisticsChart.by(List<PieWeight> data) {
    return new PieStatisticsChart(
      _fetchData(data),
    );
  }

  @override
  Widget build(BuildContext context) {
    return new charts.PieChart(seriesList,
        animate: true,
        animationDuration: Duration(milliseconds: 1000),
        defaultRenderer: new charts.ArcRendererConfig(
            arcWidth: 60,
            arcRendererDecorators: [new charts.ArcLabelDecorator()]));
  }

  /// Создает серию для диаграммы
  static List<charts.Series<PieWeight, String>> _fetchData(List<PieWeight> _data) {
    return [
      new charts.Series<PieWeight, String>(
        id: 'Weight',
        domainFn: (PieWeight item, _) => item.importance,
        measureFn: (PieWeight item, _) => item.count,
        data: _data,
        labelAccessorFn: (PieWeight row, _) => '${row.importance}: ${row.count}',
      )
    ];
  }
}

/// Модель данных
class PieWeight {
  final String importance;
  final int count;

  PieWeight(this.importance, this.count);
}
