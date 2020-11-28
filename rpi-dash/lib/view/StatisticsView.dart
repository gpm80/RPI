import 'package:flutter/material.dart';
import 'package:rpi_dash/common/charts/DonutAutoLabelChart.dart';
import 'package:rpi_dash/common/charts/GroupedStackedBarChart.dart';
import 'package:rpi_dash/common/charts/SimpleTimeSeriesChart.dart';

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
          _getItem('Динамика появления новых заявок',
              SimpleTimeSeriesChart.byDate(DateTime.now())),
          _getItem('Динамика', DonutAutoLabelChart.withSampleData()),
          _getItem('Динамика', GroupedStackedBarChart.withSampleData()),
          _getItem('Динамика', SimpleTimeSeriesChart.byDate(DateTime.now())),
        ]);
  }

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
