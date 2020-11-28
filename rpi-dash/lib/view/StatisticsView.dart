import 'package:flutter/material.dart';
import 'package:rpi_dash/common/charts/BarTimeSeriesChart.dart';
import 'package:rpi_dash/common/charts/PieStatisticsChart.dart';
import 'package:rpi_dash/common/charts/SimpleTimeSeriesChart.dart';
import 'package:rpi_dash/http/Mapping.dart';
import 'package:rpi_dash/http/TestSeries.dart';

class StatisticsView extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return GridView.count(
        crossAxisCount: 2,
        childAspectRatio: 1.7,
        padding: const EdgeInsets.all(4.0),
        mainAxisSpacing: 5.0,
        crossAxisSpacing: 5.0,
        children: [
          _getItem(
              'Динамика появления новых заявок',
              SimpleTimeSeriesChart.by(
                  Mapping.mapTimeSeries(TestSeries.timeSeries()))),
          _getItem('Распределение индекса важности за квартал',
              PieStatisticsChart.by(Mapping.mapWeight(TestSeries.pieWeight()))),
          _getItem(
              'Подача идей за 2020 год ',
              BarTimeSeriesChart.by(
                  Mapping.mapMonth(TestSeries.monthSeries()))),
          _getItem(
              'Идеи на этапе внедрения',
              BarTimeSeriesChart.by(
                  Mapping.mapMonth(TestSeries.monthSeriesRelease()))),
        ]);
  }

  /// Оборачивает элементы графиков
  Widget _getItem(String title, Widget widget) {
    return Container(
        padding: EdgeInsets.all(5.0),
        child: Column(
          children: [
            Container(
              padding: EdgeInsets.all(5.0),
              child: Text(title),
            ),
            Expanded(child: widget)
          ],
        ),
        decoration: BoxDecoration(
          color: Colors.grey[200],
          border: Border.all(
            color: Colors.grey[300],
            width: 1,
          ),
          borderRadius: BorderRadius.circular(12),
        ));
  }
}
