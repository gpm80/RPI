import 'package:flutter/cupertino.dart';
import 'package:rpi_dash/http/Mapping.dart';
import 'package:rpi_dash/http/TestSeries.dart';

import 'TopList.dart';

class TopListView extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Container(
      child: Row(
        children: [
          Expanded(
            flex: 50,
            child: TopList.of(Mapping.mapIdea(TestSeries.ideaList())),
          ),
          Expanded(
            flex: 50,
            child: Center(child: Text('1')),
          ),
        ],
      ),
    );
  }
}
