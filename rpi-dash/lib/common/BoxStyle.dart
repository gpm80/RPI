import 'package:flutter/material.dart';

class BoxStyle {
  static BoxDecoration commonBox() {
    return BoxDecoration(
      color: Colors.grey[200],
      border: Border.all(
        color: Colors.grey[300],
        width: 1,
      ),
      borderRadius: BorderRadius.circular(12),
    );
  }

  static Card commonCard(Widget widget, {EdgeInsetsGeometry padding}) {
    EdgeInsetsGeometry pad = padding ?? EdgeInsets.all(5.0);
    return Card(
        color: Colors.grey[300],
        elevation: 2.0,
        child: Container(padding: pad, child: widget));
  }
}
