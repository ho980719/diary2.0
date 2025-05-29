import { Box, Button, Container, Stack, TextField, Typography } from "@mui/material";
import { useState } from "react";
import { useNavigate } from "react-router-dom";

export default function SignIn() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();

  const handleLogin = (e: React.FormEvent) => {
    e.preventDefault();
    console.log({ email, password });
  };

  const handleSignupRedirect = () => {
    navigate("/users/sign-up");
  };

  return (
    <Container maxWidth="xs">
      <Box mt={8}>
        <Typography variant="h5" gutterBottom>
          로그인
        </Typography>
        <form onSubmit={handleLogin}>
          <TextField
            fullWidth
            label="이메일"
            margin="normal"
            type="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />
          <TextField
            fullWidth
            label="비밀번호"
            type="password"
            margin="normal"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
          <Stack spacing={2} mt={2}>
            <Button fullWidth variant="contained" color="primary" type="submit">
              로그인
            </Button>
            <Button fullWidth variant="outlined" color="secondary" onClick={handleSignupRedirect}>
              회원가입
            </Button>
          </Stack>
        </form>
      </Box>
    </Container>
  );
}
