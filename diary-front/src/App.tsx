import { Route, Routes } from "react-router-dom";
import Index from "./pages";
import Header from "./pages/layout/Header.tsx";
import { Container } from "@mui/material";
import Box from "@mui/material/Box";
import SignIn from "./pages/users/SignIn.tsx";
import SignUp from "./pages/users/SignUp.tsx";
import ChatRooms from "./pages/chat/ChatRooms.tsx";
import ChatRoom from "./pages/chat/ChatRoom.tsx";

function App() {
  return (
    <>
      <Box sx={{ maxWidth: 1, bgcolor: "background.paper" }}>
        <Header />
        <Container sx={{ mt: 4 }} maxWidth="lg">
          <Routes>
            <Route path="/" element={<Index />} />
            <Route path="users">
              <Route path="sign-in" element={<SignIn />} />
              <Route path="sign-up" element={<SignUp />} />
            </Route>
            <Route path="chat">
              <Route path="rooms" element={<ChatRooms />} />
              <Route path="room" element={<ChatRoom username="5" roomId="1" />} />
            </Route>
          </Routes>
        </Container>
      </Box>
    </>
  );
}

export default App;
