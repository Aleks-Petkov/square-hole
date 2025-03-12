FROM clojure
COPY . /app
WORKDIR /app
CMD ["clj", "-X", "main/main"]
