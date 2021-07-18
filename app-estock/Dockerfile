FROM node:12.16.1-alpine As builder
WORKDIR /usr/src/app
RUN npm install
COPY . .
RUN npm run build --prod
FROM nginx:1.15.8-alpine
COPY --from=builder /usr/src/app/dist/out-tsc/ /usr/share/nginx/html