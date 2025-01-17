import 'package:flutter/material.dart';
import 'package:rpi_dash/common/BoxStyle.dart';
import 'package:rpi_dash/common/charts/BarTimeSeriesChart.dart';
import 'package:rpi_dash/common/charts/PieStatisticsChart.dart';
import 'package:rpi_dash/common/charts/SimpleTimeSeriesChart.dart';
import 'package:rpi_dash/http/Mapping.dart';
import 'package:rpi_dash/http/TestSeries.dart';

class StatisticsView extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return GridView.count(
        crossAxisCount: 1,
        childAspectRatio: 1.2,
        padding: const EdgeInsets.all(4.0),
        mainAxisSpacing: 5.0,
        crossAxisSpacing: 5.0,
        children: [
          _getItem(
              'Подача идей за 2020 год ',
              BarTimeSeriesChart.by(
                  Mapping.mapMonth(TestSeries.monthSeries()))),
          _getItem('Распределение индекса важности за квартал',
              PieStatisticsChart.by(Mapping.mapWeight(TestSeries.pieWeight()))),
          _getItem(
              'Идеи на этапе внедрения',
              BarTimeSeriesChart.by(
                  Mapping.mapMonth(TestSeries.monthSeriesRelease()))),
          _getItem(
              'Динамика появления новых заявок',
              SimpleTimeSeriesChart.by(
                  Mapping.mapTimeSeries(TestSeries.timeSeries()))),
        ]);
  }

  /// Оборачивает элементы графиков
  Widget _getItem(String title, Widget widget) {
    return BoxStyle.commonCard(Column(
      children: [
        Container(
          padding: EdgeInsets.all(5.0),
          child: Text(title),
        ),
        Expanded(child: widget)
      ],
    ));
  }
}
