import 'package:flutter/material.dart';
import 'package:rpi_dash/common/charts/DonutAutoLabelChart.dart';
import 'package:rpi_dash/common/charts/GroupedStackedBarChart.dart';
import 'package:rpi_dash/common/charts/SimpleTimeSeriesChart.dart';

class HomeView extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return GridView.count(
        crossAxisCount: 2,
        childAspectRatio: 1.7,
        padding: const EdgeInsets.all(4.0),
        mainAxisSpacing: 5.0,
        crossAxisSpacing: 5.0,
        children: [
          SimpleTimeSeriesChart.withSampleData(),
          GroupedStackedBarChart.withSampleData(),
          DonutAutoLabelChart.withSampleData(),
          SimpleTimeSeriesChart.withSampleData(),
        ]
            .map((e) => Container(
                child: e,
                decoration: BoxDecoration(
                  color: Colors.grey[200],
                  border: Border.all(
                    color: Colors.grey[300],
                    width: 1,
                  ),
                  borderRadius: BorderRadius.circular(12),
                )))
            .toList());
  }

//
//
//      Row(
////      crossAxisAlignment: CrossAxisAlignment.stretch,
//      children: [
//        Column(
//          children: [
//            Column(
//              children: [
//                LineChart(),
//                Text('2'),
//              ],
//            ),
//          ],
//        ),
//        Column(
//          children: [
//            Text('3'),
//            Text('4'),
//          ],
//        )
//      ],
//    );
//  }
}
