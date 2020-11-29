import 'package:flutter/cupertino.dart';
import 'package:rpi_dash/http/Mapping.dart';
import 'package:rpi_dash/http/TestSeries.dart';
import 'package:rpi_dash/model/Idea.dart';
import 'package:rpi_dash/view/elements/PreviewIdea.dart';

import 'elements/TopList.dart';

class TopListView extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return TopListViewState();
  }
}

class TopListViewState extends State<TopListView> {
  Idea preview;
  List<Idea> map = Mapping.mapIdea(TestSeries.ideaList());

  @override
  Widget build(BuildContext context) {
    if (preview == null) {
      return Container(
          child: TopList.of(map, (idea) {
      setState(() {
      preview = idea;
      });
      }),
    );
    } else {
    return Container(
    child: PreviewIdea.of(preview, () {
    setState(() {
    preview = null;
    });
    }));
    }
  }
}
