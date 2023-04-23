import 'package:flutter/material.dart';

class GetNamePage extends StatefulWidget {
  @override
  _GetNamePageState createState() => _GetNamePageState();
}

class _GetNamePageState extends State<GetNamePage> {
  String _selectedOption = '';
  final TextEditingController _nameController = TextEditingController();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Get Name')),
      body: Column(
        children: [
          RadioListTile<String>(
            title: const Text("Mother"),
            value: "Mother",
            groupValue: _selectedOption,
            onChanged: (String? value) {
              setState(() {
                _selectedOption = value!;
              });
            },
          ),
          RadioListTile<String>(
            title: const Text("Father"),
            value: "Father",
            groupValue: _selectedOption,
            onChanged: (String? value) {
              setState(() {
                _selectedOption = value!;
              });
            },
          ),
          RadioListTile<String>(
            title: const Text("Cat"),
            value: "Cat",
            groupValue: _selectedOption,
            onChanged: (String? value) {
              setState(() {
                _selectedOption = value!;
              });
            },
          ),
          RadioListTile<String>(
            title: const Text("Dog"),
            value: "Dog",
            groupValue: _selectedOption,
            onChanged: (String? value) {
              setState(() {
                _selectedOption = value!;
              });
            },
          ),
          Padding(
            padding: const EdgeInsets.all(8.0),
            child: TextField(
              controller: _nameController,
              decoration: const InputDecoration(
                labelText: 'Enter name',
              ),
            ),
          ),
          ElevatedButton(
            onPressed: () {
              if (_selectedOption.isNotEmpty &&
                  _nameController.text.isNotEmpty) {
                Navigator.pop(context,
                    "$_selectedOption's Name: ${_nameController.text}");
              }
            },
            child: const Text('Send'),
          ),
        ],
      ),
    );
  }
}
