package commandManagers;

import java.time.LocalDate;
import java.util.Scanner;

import models.Color;
import models.Coordinates;
import models.FormOfEducation;
import models.Location;
import models.Person;
import models.StudyGroup;

public class InputReader {
    private final Scanner scanner;
    private final CommandMode mode;

    public InputReader(Scanner scanner, CommandMode mode) {
        this.scanner = scanner;
        this.mode = mode;
    }

    public void SetFieldWithRetry(StudyGroup studyGroup, Runnable setter, String fieldName) {
        while (true) {
            try {
                setter.run();
                break;
            } catch (Exception e) {
                if (mode == CommandMode.CLI_UserMode) {
                    System.out.println("Ошибка в поле '" + fieldName + "': " + e.getMessage() + ". Попробуйте снова.");
                } else {
                    throw e;
                }
            }
        }
    }

    public String readName(String defaultValue) {
        if (mode == CommandMode.CLI_UserMode) {
            String prompt = "Введите название группы";
            if (defaultValue != null) prompt += " (пробел для '" + defaultValue + "')";
            System.out.print(prompt + " > ");
        }
        String input = scanner.nextLine().trim();
        System.out.println("DEBUG: Read name: " + input + " (mode: " + mode + ")");
        if (mode != CommandMode.CLI_UserMode && input.isEmpty() && defaultValue != null) {
            return defaultValue;
        }
        return input;
    }

    public Coordinates readCoordinates(Coordinates defaultValue) {
        while (true) {
            try {
                Long x, y;

                // Обработка X
                if (mode == CommandMode.CLI_UserMode) {
                    String xPrompt = "Введите координату X (максимальное значение 648)";
                    if (defaultValue != null) xPrompt += " (пробел для " + defaultValue.getX() + ")";
                    System.out.print(xPrompt + " > ");
                }
                String xInput = scanner.nextLine().trim();
                System.out.println("DEBUG: Read X: " + xInput + " (mode: " + mode + ")");
                x = (defaultValue != null && xInput.isEmpty())
                        ? defaultValue.getX()
                        : Long.parseLong(xInput);
                if (x > 648) throw new IllegalArgumentException("X не может быть больше 648");

                // Обработка Y
                if (mode == CommandMode.CLI_UserMode) {
                    String yPrompt = "Введите координату Y";
                    if (defaultValue != null) yPrompt += " (пробел для " + defaultValue.getY() + ")";
                    System.out.print(yPrompt + " > ");
                }
                String yInput = scanner.nextLine().trim();
                System.out.println("DEBUG: Read Y: " + yInput + " (mode: " + mode + ")");
                y = (defaultValue != null && yInput.isEmpty())
                        ? defaultValue.getY()
                        : Long.parseLong(yInput);

                return new Coordinates(x, y);
            } catch (IllegalArgumentException e) {
                if (mode == CommandMode.CLI_UserMode) {
                    System.out.println(e.getMessage());
                } else {
                    throw e;
                }
            }
        }
    }

    public Long readStudentsCount(Long defaultValue) {
        while (true) {
            try {
                if (mode == CommandMode.CLI_UserMode) {
                    String prompt = "Введите количество студентов";
                    if (defaultValue != null) prompt += " (пробел для " + defaultValue + ")";
                    System.out.print(prompt + " > ");
                }
                String input = scanner.nextLine().trim();
                System.out.println("DEBUG: Read students count: " + input + " (mode: " + mode + ")");
                long count;
                if (mode != CommandMode.CLI_UserMode && input.isEmpty() && defaultValue != null) {
                    count = defaultValue;
                } else {
                    count = Long.parseLong(input);
                }
                if (count <= 0) throw new IllegalArgumentException("Количество должно быть больше 0");
                return count;
            } catch (IllegalArgumentException e) {
                if (mode == CommandMode.CLI_UserMode) {
                    System.out.println(e.getMessage());
                } else {
                    throw e;
                }
            }
        }
    }

    public Integer readExpelledStudents(Integer defaultValue) {
        while (true) {
            try {
                if (mode == CommandMode.CLI_UserMode) {
                    String prompt = "Введите количество отчисленных студентов";
                    if (defaultValue != null) prompt += " (пробел для " + defaultValue + ")";
                    System.out.print(prompt + " > ");
                }
                String input = scanner.nextLine().trim();
                System.out.println("DEBUG: Read expelled students: " + input + " (mode: " + mode + ")");
                int count;
                if (mode != CommandMode.CLI_UserMode && input.isEmpty() && defaultValue != null) {
                    count = defaultValue;
                } else {
                    count = Integer.parseInt(input);
                }
                if (count <= 0) throw new IllegalArgumentException("Количество должно быть больше 0");
                return count;
            } catch (IllegalArgumentException e) {
                if (mode == CommandMode.CLI_UserMode) {
                    System.out.println(e.getMessage());
                } else {
                    throw e;
                }
            }
        }
    }

    public Integer readTransferredStudents(Integer defaultValue) {
        while (true) {
            try {
                if (mode == CommandMode.CLI_UserMode) {
                    String prompt = "Введите количество переведенных студентов";
                    if (defaultValue != null) prompt += " (пробел для " + defaultValue + ")";
                    System.out.print(prompt + " > ");
                }
                String input = scanner.nextLine().trim();
                System.out.println("DEBUG: Read transferred students: " + input + " (mode: " + mode + ")");
                int count;
                if (mode != CommandMode.CLI_UserMode && input.isEmpty() && defaultValue != null) {
                    count = defaultValue;
                } else {
                    count = Integer.parseInt(input);
                }
                if (count <= 0) throw new IllegalArgumentException("Количество должно быть больше 0");
                return count;
            } catch (IllegalArgumentException e) {
                if (mode == CommandMode.CLI_UserMode) {
                    System.out.println(e.getMessage());
                } else {
                    throw e;
                }
            }
        }
    }

    public FormOfEducation readFormOfEducation(FormOfEducation defaultValue) {
        while (true) {
            try {
                if (mode == CommandMode.CLI_UserMode) {
                    String prompt = "Выберите форму обучения";
                    if (defaultValue != null) prompt += " (пробел для " + defaultValue + "):";
                    else prompt += ":";
                    System.out.println(prompt);
                    for (FormOfEducation form : FormOfEducation.values()) {
                        System.out.println(form.ordinal() + 1 + " — " + form);
                    }
                    System.out.print("> ");
                }
                String input = scanner.nextLine().trim();
                System.out.println("DEBUG: Read form of education: " + input + " (mode: " + mode + ")");
                if (mode != CommandMode.CLI_UserMode && input.isEmpty() && defaultValue != null) {
                    return defaultValue;
                }
                try {
                    int choice = Integer.parseInt(input);
                    if (choice > 0 && choice <= FormOfEducation.values().length) {
                        return FormOfEducation.values()[choice - 1];
                    }
                } catch (NumberFormatException ignored) {}
                return FormOfEducation.valueOf(input);
            } catch (IllegalArgumentException e) {
                if (mode == CommandMode.CLI_UserMode) {
                    System.out.println("Ошибка! Выберите существующий вариант");
                } else {
                    throw e;
                }
            }
        }
    }

    public Person readGroupAdmin(Person defaultValue) {
        try {
            Person admin = new Person();
            if (mode == CommandMode.CLI_UserMode) {
                String namePrompt = "Введите имя администратора";
                if (defaultValue != null && defaultValue.getName() != null) {
                    namePrompt += " (пробел для '" + defaultValue.getName() + "')";
                }
                System.out.print(namePrompt + " > ");
            }
            String nameInput = scanner.nextLine().trim();
            System.out.println("DEBUG: Read admin name: " + nameInput + " (mode: " + mode + ")");
            if (mode != CommandMode.CLI_UserMode && nameInput.isEmpty() && defaultValue != null && defaultValue.getName() != null) {
                admin.setName(defaultValue.getName());
            } else {
                admin.setName(nameInput);
            }

            if (mode == CommandMode.CLI_UserMode) {
                String birthdayPrompt = "Введите дату рождения (в формате YYYY-MM-DD или пустую строку)";
                if (defaultValue != null && defaultValue.getBirthday() != null) {
                    birthdayPrompt += " (пробел для " + defaultValue.getBirthday() + ")";
                }
                System.out.println(birthdayPrompt + " > ");
            }
            String birthdayStr = scanner.nextLine().trim();
            System.out.println("DEBUG: Read admin birthday: " + birthdayStr + " (mode: " + mode + ")");
            if (mode != CommandMode.CLI_UserMode && birthdayStr.isEmpty() && defaultValue != null && defaultValue.getBirthday() != null) {
                admin.setBirthday(defaultValue.getBirthday());
            } else if (!birthdayStr.isEmpty()) {
                admin.setBirthday(LocalDate.parse(birthdayStr));
            }

            if (mode == CommandMode.CLI_UserMode) {
                String passportPrompt = "Введите номер паспорта (не более 26 символов или пустую строку)";
                if (defaultValue != null && defaultValue.getPassportID() != null) {
                    passportPrompt += " (пробел для '" + defaultValue.getPassportID() + "')";
                }
                System.out.println(passportPrompt + " > ");
            }
            String passportID = scanner.nextLine().trim();
            System.out.println("DEBUG: Read admin passport: " + passportID + " (mode: " + mode + ")");
            if (mode != CommandMode.CLI_UserMode && passportID.isEmpty() && defaultValue != null && defaultValue.getPassportID() != null) {
                admin.setPassportID(defaultValue.getPassportID());
            } else if (!passportID.isEmpty()) {
                admin.setPassportID(passportID);
            }

            admin.setEyeColor(readEyeColor(defaultValue != null ? defaultValue.getEyeColor() : null));
            admin.setLocation(readLocation(defaultValue != null ? defaultValue.getLocation() : null));

            return admin;
        } catch (IllegalArgumentException e) {
            if (mode == CommandMode.CLI_UserMode) {
                System.out.println("Ошибка: " + e.getMessage());
                return readGroupAdmin(defaultValue);
            } else {
                throw e;
            }
        }
    }

    public Color readEyeColor(Color defaultValue) {
        while (true) {
            try {
                if (mode == CommandMode.CLI_UserMode) {
                    String prompt = "Выберите цвет глаз";
                    if (defaultValue != null) prompt += " (пробел для " + defaultValue + "):";
                    else prompt += ":";
                    System.out.println(prompt);
                    for (Color color : Color.values()) {
                        System.out.println(color.ordinal() + 1 + " — " + color);
                    }
                    System.out.print("> ");
                }
                String input = scanner.nextLine().trim();
                System.out.println("DEBUG: Read eye color: " + input + " (mode: " + mode + ")");
                if (mode != CommandMode.CLI_UserMode && input.isEmpty() && defaultValue != null) {
                    return defaultValue;
                }
                try {
                    int choice = Integer.parseInt(input);
                    if (choice > 0 && choice <= Color.values().length) {
                        return Color.values()[choice - 1];
                    }
                } catch (NumberFormatException ignored) {}
                return Color.valueOf(input);
            } catch (IllegalArgumentException e) {
                if (mode == CommandMode.CLI_UserMode) {
                    System.out.println("Ошибка! Выберите существующий цвет");
                } else {
                    throw e;
                }
            }
        }
    }

    public Location readLocation(Location defaultValue) {
        while (true) {
            try {
                Float x, y, z;

                if (mode == CommandMode.CLI_UserMode) {
                    String xPrompt = "Введите координату X локации";
                    if (defaultValue != null) xPrompt += " (пробел для " + defaultValue.getX() + ")";
                    System.out.print(xPrompt + " > ");
                }
                String xInput = scanner.nextLine().trim();
                System.out.println("DEBUG: Read location X: " + xInput + " (mode: " + mode + ")");
                x = (mode != CommandMode.CLI_UserMode && xInput.isEmpty() && defaultValue != null)
                        ? defaultValue.getX()
                        : Float.parseFloat(xInput);

                if (mode == CommandMode.CLI_UserMode) {
                    String yPrompt = "Введите координату Y локации";
                    if (defaultValue != null) yPrompt += " (пробел для " + defaultValue.getY() + ")";
                    System.out.print(yPrompt + " > ");
                }
                String yInput = scanner.nextLine().trim();
                System.out.println("DEBUG: Read location Y: " + yInput + " (mode: " + mode + ")");
                y = (mode != CommandMode.CLI_UserMode && yInput.isEmpty() && defaultValue != null)
                        ? defaultValue.getY()
                        : Float.parseFloat(yInput);

                if (mode == CommandMode.CLI_UserMode) {
                    String zPrompt = "Введите координату Z локации";
                    if (defaultValue != null) zPrompt += " (пробел для " + defaultValue.getZ() + ")";
                    System.out.print(zPrompt + " > ");
                }
                String zInput = scanner.nextLine().trim();
                System.out.println("DEBUG: Read location Z: " + zInput + " (mode: " + mode + ")");
                z = (mode != CommandMode.CLI_UserMode && zInput.isEmpty() && defaultValue != null)
                        ? defaultValue.getZ()
                        : Float.parseFloat(zInput);

                return new Location(x, y, z);
            } catch (NumberFormatException e) {
                if (mode == CommandMode.CLI_UserMode) {
                    System.out.println("Ошибка! Введите корректные числа");
                } else {
                    throw e;
                }
            }
        }
    }
}