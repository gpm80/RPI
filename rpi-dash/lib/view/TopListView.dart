import 'package:flutter/cupertino.dart';
import 'package:rpi_dash/http/Mapping.dart';
import 'package:rpi_dash/http/TestSeries.dart';
import 'package:rpi_dash/model/Idea.dart';
import 'package:rpi_dash/view/elements/PreviewIdea.dart';

import 'TopList.dart';

class TopListView extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return TopListViewState();
  }
}

class TopListViewState extends State<TopListView> {
  Idea preview;

  @override
  Widget build(BuildContext context) {
    return Container(
      child: Row(
        children: [
          Expanded(
            flex: 50,
            child: TopList.of(Mapping.mapIdea(TestSeries.ideaList()), (idea) {
              setState(() {
                preview = idea;
              });
            }),
          ),
          Expanded(
            flex: 50,
            child: PreviewIdea.of(preview),
          ),
        ],
      ),
    );
  }
}
