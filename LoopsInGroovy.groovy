String message = ''
for (int i = 0; i < 5; i++) {
    //message += 'Hi '
    for(int j = 0; j < 5; j++)
    {
        message = i.toString() + j.toString() + '\n'
        print(message)
    }
}