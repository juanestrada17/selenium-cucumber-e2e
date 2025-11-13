# ============================
# Base Image: Maven + JDK 21
# ============================
FROM maven:3.9.6-eclipse-temurin-21

# Set working directory
WORKDIR /app

# ----------------------------
# Install Chrome + ChromeDriver
# ----------------------------

# Install dependencies
RUN apt-get update && apt-get install -y \
    wget \
    gnupg \
    unzip \
    curl \
    ca-certificates \
    --no-install-recommends

# Add Google Chrome repo key
RUN wget -q -O - https://dl.google.com/linux/linux_signing_key.pub | apt-key add -

# Add Chrome repository
RUN sh -c 'echo "deb [arch=amd64] http://dl.google.com/linux/chrome/deb/ stable main" >> /etc/apt/sources.list.d/google-chrome.list'

# Install Google Chrome (stable)
RUN apt-get update && apt-get install -y google-chrome-stable

# Download ChromeDriver matching Chrome version
RUN CHROME_VERSION=$(google-chrome --version | awk '{print $3}' | cut -d'.' -f1) && \
    DRIVER_VERSION=$(wget -qO- "https://googlechromelabs.github.io/chrome-for-testing/LATEST_RELEASE_${CHROME_VERSION}") && \
    wget -O chromedriver.zip https://edgedl.me.gvt1.com/edgedl/chrome/chrome-for-testing/${DRIVER_VERSION}/linux64/chromedriver-linux64.zip && \
    unzip chromedriver.zip && \
    mv chromedriver-linux64/chromedriver /usr/local/bin/ && \
    chmod +x /usr/local/bin/chromedriver && \
    rm -rf chromedriver.zip chromedriver-linux64

# ----------------------------
# Copy Maven project
# ----------------------------

# Copy pom.xml and download dependencies first (Docker cache optimization)
COPY pom.xml .
RUN mvn -B dependency:go-offline

# Copy the rest of your project
COPY src ./src

# ----------------------------
# Default command = run tests
# ----------------------------
CMD ["mvn", "test"]
