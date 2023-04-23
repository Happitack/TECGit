import 'package:flutter/material.dart';

class GetColorPage extends StatefulWidget {
  const GetColorPage({super.key});

  @override
  // ignore: library_private_types_in_public_api
  _GetColorPageState createState() => _GetColorPageState();
}

class _GetColorPageState extends State<GetColorPage> {
  int redValue = 0;
  int greenValue = 0;
  int blueValue = 0;

  List<int> getColorValues() {
    List<int> values = [];
    for (int i = 0; i <= 255; i += 16) {
      values.add(i);
    }
    return values;
  }

  DropdownButton<int> buildColorDropdown({
    required int value,
    required ValueChanged<int?> onChanged,
  }) {
    final colorValues = getColorValues();

    return DropdownButton<int>(
      value: value,
      items: colorValues.map((int value) {
        return DropdownMenuItem<int>(
          value: value,
          child: Text(value.toRadixString(16).padLeft(2, '0').toUpperCase()),
        );
      }).toList(),
      onChanged: onChanged,
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Get Ones Color'),
      ),
      body: Column(
        mainAxisAlignment: MainAxisAlignment.spaceEvenly,
        children: [
          Container(
            width: double.infinity,
            height: 100,
            color: Color.fromRGBO(redValue, greenValue, blueValue, 1),
            alignment: Alignment.center,
            child: const Text(
              'Selected Color',
              style: TextStyle(fontSize: 24, color: Colors.white),
            ),
          ),
          Row(
            mainAxisAlignment: MainAxisAlignment.spaceEvenly,
            children: [
              buildColorDropdown(
                value: redValue,
                onChanged: (value) => setState(() => redValue = value!),
              ),
              buildColorDropdown(
                value: greenValue,
                onChanged: (value) => setState(() => greenValue = value!),
              ),
              buildColorDropdown(
                value: blueValue,
                onChanged: (value) => setState(() => blueValue = value!),
              ),
            ],
          ),
          ElevatedButton(
            onPressed: () {
              Navigator.pop(
                context,
                Color.fromRGBO(redValue, greenValue, blueValue, 1),
              );
            },
            child: const Text('Send Color'),
          ),
        ],
      ),
    );
  }
}
