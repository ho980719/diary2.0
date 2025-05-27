import react from "@vitejs/plugin-react";
import { defineConfig } from "vite";

export default defineConfig({
  plugins: [react()],
  define: {
    global: "window", // <- 이게 핵심
  },
  server: {
    proxy: {
      "/api": {
        target: "http://localhost:8080", // diary-api
        changeOrigin: true,
      },
      "/auth": {
        target: "http://localhost:8081", // diary-auth
        changeOrigin: true,
      },
    },
  },
});
