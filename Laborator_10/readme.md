# Laboratory no. 10

## Compulsory 

- Create the project ServerApplication. This will contain (at least) the classes: GameServer and ClientThread.(*solved*)
- Create the class GameServer. An instance of this class will create a ServerSocket running at a specified port. The server will receive requests (commands) from clients and it will execute them.(*solved*)
- Create the class ClientThread. An instance of this class will be responsible with communicating with a client Socket. If the server receives the command stop it will stop and will return to the client the respons "Server stopped", otherwise it return: "Server received the request ... ".(*solved*)
- Create the project ClientApplication. This will contain (at least) the class: GameClient.(*solved*)
- Create the class GameClient. An instance of this class will read commands from the keyboard and it will send them to the server. The client stops when it reads from the keyboard the string "exit". (*solved*)