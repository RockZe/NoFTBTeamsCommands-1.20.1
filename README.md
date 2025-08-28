# No FTBTeams Commands (Fabric 1.20.1)

Блокирует выполнение игроками команд `/ftbteams` (и всех подкоманд), если у них нет пермишна `noftbteams.bypass`.

## Требования
- Java 17
- Fabric Loader 0.15.x
- Fabric API 1.20.1
- (Рекомендуется) LuckPerms + fabric-permissions-api-v0

## Сборка
```bash
./gradlew build
```
Готовый jar: `build/libs/no-ftbteams-commands-1.0.0.jar`

## Установка
1. Поместите jar в папку `mods/` сервера.
2. Выдайте байпас персоналу:
```bash
/lp group staff permission set noftbteams.bypass true
```

## Как работает
Mixin в `net.minecraft.commands.Commands#performCommand(...)` перехватывает исходную строку команды и, если она начинается с `ftbteams`, отменяет выполнение и выводит сообщение.
Это не затрагивает ванильную `/team`.