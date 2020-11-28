class Idea {
  final String title;
  final String type;
  final String typeDesc;
  final String description;
  final int importance;
  final int collaborators;
  DateTime createDate;

  Idea(this.title, this.type, this.typeDesc, this.description, this.importance,
      this.collaborators, String date) {
    this.createDate = DateTime.parse(date);
  }

  @override
  String toString() {
    return 'Idea{title: $title, type: $type, description: $description, importance: $importance, collaborators: $collaborators, createDate: $createDate}';
  }

  factory Idea.fromJson(Map<String, dynamic> json) => Idea(
      json['title'],
      json['type'],
      json['typeDesc'],
      json['description'],
      json['importance'],
      json['collaborators'],
      json['date']);
}
