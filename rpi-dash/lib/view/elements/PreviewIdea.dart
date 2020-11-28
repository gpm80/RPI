import 'package:flutter/material.dart';
import 'package:rpi_dash/model/Idea.dart';

class PreviewIdea extends StatelessWidget {
  final Idea idea;

  PreviewIdea(this.idea);

  factory PreviewIdea.of(Idea idea) {
    return PreviewIdea(idea);
  }

  @override
  Widget build(BuildContext context) {
    if (idea == null) {
      return Center(child: Text('Не выбрано'));
    }
    return Card(
      child: Container(
        child: Column(
          children: [
            _actionButton(),
            Text(idea.title,
                style: DefaultTextStyle.of(context).style.apply(
                      fontSizeFactor: 1.8,
                    )),
            Align(
                alignment: Alignment.topLeft,
                child: Text(idea.typeDesc,
                    style: DefaultTextStyle.of(context).style.apply(
                          fontSizeFactor: 1.2,
                        ))),
            Container(
              padding: EdgeInsets.only(top: 5.0),
              child: Text(
                'Описание',
                style: TextStyle(fontStyle: FontStyle.italic),
              ),
            ),
            Flexible(child: Text(idea.description)),
          ],
        ),
      ),
    );
  }

  Widget _actionButton() {
    return Container(
        padding: EdgeInsets.all(10.0),
        child: Row(
          children: [
            FlatButton(
                child: new Text('На доработку'),
                onPressed: () {},
                color: new Color.fromRGBO(240, 240, 240, 1.0),
                shape: new RoundedRectangleBorder(
                    side: new BorderSide(color: Colors.yellow, width: 2.0),
                    borderRadius: new BorderRadius.circular(30.0))),
            FlatButton(
                child: new Text('Отклонить'),
                onPressed: () {},
                color: new Color.fromRGBO(240, 240, 240, 1.0),
                shape: new RoundedRectangleBorder(
                    side: new BorderSide(color: Colors.red, width: 2.0),
                    borderRadius: new BorderRadius.circular(30.0))),
            FlatButton(
                child: new Text('На внедрение'),
                onPressed: () {},
                color: new Color.fromRGBO(240, 240, 240, 1.0),
                shape: new RoundedRectangleBorder(
                    side: new BorderSide(color: Colors.green, width: 2.0),
                    borderRadius: new BorderRadius.circular(30.0))),
          ],
        ));
  }
}
