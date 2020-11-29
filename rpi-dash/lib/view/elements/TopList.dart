import 'package:flutter/material.dart';
import 'package:rpi_dash/common/BoxStyle.dart';
import 'package:rpi_dash/model/Idea.dart';

class TopList extends StatelessWidget {
  final List<Idea> items;
  final void Function(Idea idea) changeListener;

  TopList(this.items, this.changeListener);

  factory TopList.of(List<Idea> data, void Function(Idea idea) call) {
    return TopList(data, call);
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
    return BoxStyle.commonCard(
        ListTile(
          leading: CircleAvatar(
            backgroundImage: _chooseImage(idea),
          ),
          title: _makeTitle(idea),
          subtitle: _makeSubscript(idea),
          trailing: _getState(idea),
          onTap: () {
            changeListener(idea);
          },
        ),
        padding: EdgeInsets.zero);
  }

  ImageProvider _chooseImage(Idea idea) {
    if (idea.type == 'rele') {
      return AssetImage("rele.png");
    } else if (idea.type == 'substations') {
      return AssetImage("substations.png");
    } else if (idea.type == 'networks') {
      return AssetImage("networks.png");
    }
    return AssetImage("notfound.png");
  }

  Widget _makeTitle(Idea idea) {
    return Container(
      child: Row(
        children: [
          Flexible(child: Text('${idea.title}')),
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

  Widget _getState(Idea idea) {
    switch (idea.state) {
      case 'APPROVE':
        return Icon(
          Icons.assignment_turned_in,
          color: Colors.green,
        );
      case 'REVISION':
        return Icon(
          Icons.assignment_late,
          color: Colors.yellow[700],
        );
      case 'DECLINED':
        return Icon(
          Icons.cancel,
          color: Colors.red,
        );
    }
    return Icon(Icons.keyboard_arrow_right);
  }
}
