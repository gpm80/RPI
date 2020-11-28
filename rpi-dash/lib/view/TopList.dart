import 'package:flutter/material.dart';
import 'package:rpi_dash/common/BoxStyle.dart';
import 'package:rpi_dash/model/Idea.dart';

class TopList extends StatelessWidget {
  final List<Idea> items;

  TopList(this.items);

  factory TopList.of(List<Idea> data) {
    return TopList(data);
  }

  @override
  Widget build(BuildContext context) {
    if (items.isEmpty) {
      return Center(
          child: Text(
        "Нет данных",
        textAlign: TextAlign.center,
      ));
    }
    return ListView.builder(
      itemBuilder: (context, index) => _makeItem(context, items[index]),
      itemCount: items.length,
    );
  }

  Widget _makeItem(BuildContext context, Idea idea) {
    return Container(
        padding: EdgeInsets.all(3.0),
        decoration: BoxStyle.commonBox(),
        child: ListTile(
          leading: CircleAvatar(
            backgroundColor: _chooseColor(idea),
          ),
          title: _makeTitle(idea),
          subtitle: _makeSubscript(idea),
        ));
  }

  MaterialColor _chooseColor(Idea idea) {
    if (idea.type == 'rele') {
      return Colors.red;
    } else if (idea.type == 'substations') {
      return Colors.lightBlue;
    } else if (idea.type == 'networks') {
      return Colors.yellow;
    }
    return Colors.grey;
  }

  Widget _makeTitle(Idea idea) {
    return Container(
      child: Row(
        children: [
          Text('${idea.title}'),
          Expanded(child: Text('')),
          Text('Важность: ${idea.importance} %'),
        ],
      ),
    );
  }

  Widget _makeSubscript(Idea idea) {
    String sub = idea.description;
    if (sub.length > 100) {
      sub = sub.substring(0, 70) + '...';
    }
    return Container(
      child: Row(
        children: [
          Flexible(child: Text('Описание: $sub')),
        ],
      ),
    );
  }
}
