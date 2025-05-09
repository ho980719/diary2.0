import './App.css'
import {Route, Routes} from "react-router-dom";
import SignUp from "./pages/users/SignUp.tsx";
import Index from "./pages";

function App() {
  return (
      <Routes>
          <Route path="/" element={<Index />} />
          <Route path="/users/sign-up" element={<SignUp />} />
      </Routes>
  )
}

export default App
