# Java OOP 多人連線聊天室 (Server-Client Messenger)

這是一個專為物件導向程式設計（OOP）與網路程式設計（Network Programming）教學設計的基礎專案。本專案透過 Java 的 `Socket`、`Multi-threading` 與`物件序列化 (Serialization)`，實作一個具備多人群聊功能的伺服端與客戶端架構。

未來專案計畫延伸支援 **RTSP (Real-Time Streaming Protocol) 影音串流功能**。

## 🎯 教學核心重點
- **單一職責原則 (SRP)**：系統拆解為核心資料模型 (`Message`)、調度中心 (`ServerManager`)、連線執行緒 (`ClientHandler`) 與啟動器。
- **物件序列化**：透過 `ObjectOutputStream` 傳遞自訂物件，而非單純的字串。
- **多執行緒處理**：伺服器利用執行緒池的概念，同步處理多個客戶端的請求。

---

## 🏗️ 系統架構與封包規劃

專案依據功能劃分為三個主要封包 (Packages)：
- `com.messenger.common`: 存放伺服端與客戶端共用的物件（如 `Message.java`）。
- `com.messenger.server`: 存放伺服器端控制邏輯（如 `ChatServer`, `ServerManager`, `ClientHandler`）。
- `com.messenger.client`: 存放客戶端控制邏輯（如 `ChatClient`, `ServerListener`）。

---

## 🛠️ 開發與執行環境
- **開發工具**：Eclipse IDE
- **Java 版本**：JDK 17 或以上（本專案已在 JDK 25 環境測試通過）
- **作業系統**：Windows / macOS / Linux

---

## 🚀 執行教學與步驟

### 1. 在 Eclipse 中執行測試
1. **啟動伺服端**：在 `ChatServer.java` 點擊右鍵 ➔ `Run As` ➔ `Java Application`。
2. **啟動第一位客戶端**：在 `ChatClient.java` 點擊右鍵 ➔ `Run As` ➔ `Java Application`，於 Console 輸入暱稱（例如：Alice）。
3. **啟動第二位客戶端**：再次對 `ChatClient.java` 點擊右鍵 ➔ `Run As` ➔ `Java Application`，輸入第二個暱稱（例如：Bob）。
4. 透過 Eclipse Console 右上角的 **顯示選擇的主控台 (Display Selected Console)** 切換畫面進行群聊互動。

### 2. 匯出為可執行的 `.jar` 檔
1. 專案右鍵 ➔ `Export...` ➔ 展開 `Java` ➔ 選擇 `Runnable JAR file` ➔ `Next`。
2. **Launch configuration**：伺服端選擇 `ChatServer`，客戶端選擇 `ChatClient`。
3. **Export destination**：指定儲存路徑與檔名（如 `ChatServer.jar`），點擊 `Finish`。

### 🔤 解決 Windows CMD 中文亂碼問題
由於現代 Java 預設使用 UTF-8 編碼，在 Windows `cmd` 執行時若出現中文變成 `?A?n` 的現象，請使用以下命令列加上編碼參數啟動：

* **啟動伺服端：**
  ```bash
  java -Dfile.encoding=UTF-8 -jar ChatServer.jar
  ```
* **啟動客戶端：**
  ```bash
  java -Dfile.encoding=UTF-8 -jar ChatClient.jar
  ```

---

## 📅 下一步計畫：RTSP 影音串流擴充
- [ ] 升級 `Message` 物件以支援點陣圖與影音位元流（Byte Array）傳輸。
- [ ] 引入 `ClientUI` 圖形介面（Swing / JavaFX）以利即時渲染畫面。
- [ ] 引入第三方多媒體 API（如 JavaCV / FFmpeg）實作標準 RTSP 控制協定與 RTP 傳輸。
