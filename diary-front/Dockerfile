# 1단계: 빌드
FROM node:20 AS builder

WORKDIR /app
COPY . .
RUN npm ci && npm run build

# 2단계: 정적 서빙
FROM nginx:alpine

# Vite는 기본적으로 dist에 결과물 생성
COPY --from=builder /app/dist /usr/share/nginx/html

# (선택) custom nginx 설정 사용 시:
# COPY nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
