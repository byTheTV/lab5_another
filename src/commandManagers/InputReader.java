package commandManagers;

import java.util.Arrays;
import java.util.Scanner;

import models.Coordinates;
import models.Flat;
import models.Furnish;
import models.House;
import models.Transport;
import models.View;

public class InputReader {
    private final Scanner scanner;
    private final CommandMode mode;

    public InputReader(Scanner scanner, CommandMode mode) {
        this.scanner = scanner;
        this.mode = mode;
    }

    public void setFieldWithRetry(Flat flat, Runnable setter, String fieldName) {
        while (true) {
            try {
                setter.run();
                break;
            } catch (Exception e) {
                if (mode == CommandMode.CLI_UserMode) {
                    System.out.println("Error in field '" + fieldName + "': " + e.getMessage() + ". Try again.");
                } else {
                    throw e;
                }
            }
        }
    }

    public String readName(String defaultValue) {
        if (mode == CommandMode.CLI_UserMode) {
            String prompt = "Enter flat name";
            if (defaultValue != null) prompt += " (space for '" + defaultValue + "')";
            System.out.print(prompt + " > ");
        }
        String input = scanner.nextLine().trim();
        if (mode != CommandMode.CLI_UserMode && input.isEmpty() && defaultValue != null) {
            return defaultValue;
        }
        return input;
    }

    public Coordinates readCoordinates(Coordinates defaultValue) {
        while (true) {
            try {
                Long x, y;

                // Handle X
                if (mode == CommandMode.CLI_UserMode) {
                    String xPrompt = "Enter X coordinate (max value 648)";
                    if (defaultValue != null) xPrompt += " (space for " + defaultValue.getX() + ")";
                    System.out.print(xPrompt + " > ");
                }
                String xInput = scanner.nextLine().trim();
                x = (defaultValue != null && xInput.isEmpty())
                        ? defaultValue.getX()
                        : Long.parseLong(xInput);
                if (x > 648) throw new IllegalArgumentException("X cannot be greater than 648");

                // Handle Y
                if (mode == CommandMode.CLI_UserMode) {
                    String yPrompt = "Enter Y coordinate";
                    if (defaultValue != null) yPrompt += " (space for " + defaultValue.getY() + ")";
                    System.out.print(yPrompt + " > ");
                }
                String yInput = scanner.nextLine().trim();
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

    public Float readArea(Float defaultValue) {
        while (true) {
            try {
                if (mode == CommandMode.CLI_UserMode) {
                    String prompt = "Enter area";
                    if (defaultValue != null) prompt += " (space for " + defaultValue + ")";
                    System.out.print(prompt + " > ");
                }
                String input = scanner.nextLine().trim();
                float area;
                if (mode != CommandMode.CLI_UserMode && input.isEmpty() && defaultValue != null) {
                    area = defaultValue;
                } else {
                    area = Float.parseFloat(input);
                }
                if (area <= 0) throw new IllegalArgumentException("Area must be greater than 0");
                return area;
            } catch (IllegalArgumentException e) {
                if (mode == CommandMode.CLI_UserMode) {
                    System.out.println(e.getMessage());
                } else {
                    throw e;
                }
            }
        }
    }

    public Long readNumberOfRooms(Long defaultValue) {
        while (true) {
            try {
                if (mode == CommandMode.CLI_UserMode) {
                    String prompt = "Enter number of rooms";
                    if (defaultValue != null) prompt += " (space for " + defaultValue + ")";
                    System.out.print(prompt + " > ");
                }
                String input = scanner.nextLine().trim();
                long count;
                if (mode != CommandMode.CLI_UserMode && input.isEmpty() && defaultValue != null) {
                    count = defaultValue;
                } else {
                    count = Long.parseLong(input);
                }
                if (count <= 0) throw new IllegalArgumentException("Number of rooms must be greater than 0");
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

    public Furnish readFurnish(Furnish defaultValue) {
        while (true) {
            try {
                if (mode == CommandMode.CLI_UserMode) {
                    String prompt = "Choose furnish type";
                    if (defaultValue != null) prompt += " (space for " + defaultValue + "):";
                    else prompt += ":";
                    System.out.println(prompt);
                    for (Furnish furnish : Furnish.values()) {
                        System.out.println(furnish.ordinal() + 1 + " — " + furnish);
                    }
                    System.out.print("> ");
                }
                String input = scanner.nextLine().trim();
                if (mode != CommandMode.CLI_UserMode && input.isEmpty() && defaultValue != null) {
                    return defaultValue;
                }
                try {
                    int choice = Integer.parseInt(input);
                    if (choice > 0 && choice <= Furnish.values().length) {
                        return Furnish.values()[choice - 1];
                    }
                } catch (NumberFormatException ignored) {}
                return Furnish.valueOf(input);
            } catch (IllegalArgumentException e) {
                if (mode == CommandMode.CLI_UserMode) {
                    System.out.println("Invalid furnish type. Available types: " + Arrays.toString(Furnish.values()));
                } else {
                    throw e;
                }
            }
        }
    }

    public View readView(View defaultValue) {
        while (true) {
            try {
                if (mode == CommandMode.CLI_UserMode) {
                    String prompt = "Choose view type";
                    if (defaultValue != null) prompt += " (space for " + defaultValue + "):";
                    else prompt += ":";
                    System.out.println(prompt);
                    for (View view : View.values()) {
                        System.out.println(view.ordinal() + 1 + " — " + view);
                    }
                    System.out.print("> ");
                }
                String input = scanner.nextLine().trim();
                if (mode != CommandMode.CLI_UserMode && input.isEmpty() && defaultValue != null) {
                    return defaultValue;
                }
                try {
                    int choice = Integer.parseInt(input);
                    if (choice > 0 && choice <= View.values().length) {
                        return View.values()[choice - 1];
                    }
                } catch (NumberFormatException ignored) {}
                return View.valueOf(input);
            } catch (IllegalArgumentException e) {
                if (mode == CommandMode.CLI_UserMode) {
                    System.out.println("Invalid view type. Available types: " + Arrays.toString(View.values()));
                } else {
                    throw e;
                }
            }
        }
    }

    public Transport readTransport(Transport defaultValue) {
        while (true) {
            try {
                if (mode == CommandMode.CLI_UserMode) {
                    String prompt = "Choose transport type";
                    if (defaultValue != null) prompt += " (space for " + defaultValue + "):";
                    else prompt += ":";
                    System.out.println(prompt);
                    for (Transport transport : Transport.values()) {
                        System.out.println(transport.ordinal() + 1 + " — " + transport);
                    }
                    System.out.print("> ");
                }
                String input = scanner.nextLine().trim();
                if (mode != CommandMode.CLI_UserMode && input.isEmpty() && defaultValue != null) {
                    return defaultValue;
                }
                try {
                    int choice = Integer.parseInt(input);
                    if (choice > 0 && choice <= Transport.values().length) {
                        return Transport.values()[choice - 1];
                    }
                } catch (NumberFormatException ignored) {}
                return Transport.valueOf(input);
            } catch (IllegalArgumentException e) {
                if (mode == CommandMode.CLI_UserMode) {
                    System.out.println("Invalid transport type. Available types: " + Arrays.toString(Transport.values()));
                } else {
                    throw e;
                }
            }
        }
    }

    public House readHouse(House defaultValue) {
        while (true) {
            try {
                String name = readName(defaultValue != null ? defaultValue.getName() : null);
                Integer year = readYear(defaultValue != null ? defaultValue.getYear() : null);
                long numberOfFlatsOnFloor = readNumberOfFlatsOnFloor(defaultValue != null ? defaultValue.getNumberOfFlatsOnFloor() : null);
                Integer numberOfLifts = readNumberOfLifts(defaultValue != null ? defaultValue.getNumberOfLifts() : null);
                return new House(name, year, numberOfFlatsOnFloor, numberOfLifts);
            } catch (IllegalArgumentException e) {
                if (mode == CommandMode.CLI_UserMode) {
                    System.out.println(e.getMessage());
                } else {
                    throw e;
                }
            }
        }
    }

    private Integer readYear(Integer defaultValue) {
        while (true) {
            try {
                if (mode == CommandMode.CLI_UserMode) {
                    String prompt = "Enter year";
                    if (defaultValue != null) prompt += " (space for " + defaultValue + ")";
                    System.out.print(prompt + " > ");
                }
                String input = scanner.nextLine().trim();
                int year;
                if (mode != CommandMode.CLI_UserMode && input.isEmpty() && defaultValue != null) {
                    year = defaultValue;
                } else {
                    year = Integer.parseInt(input);
                }
                if (year <= 0 || year > 975) throw new IllegalArgumentException("Year must be greater than 0 and less than or equal to 975");
                return year;
            } catch (IllegalArgumentException e) {
                if (mode == CommandMode.CLI_UserMode) {
                    System.out.println(e.getMessage());
                } else {
                    throw e;
                }
            }
        }
    }

    private long readNumberOfFlatsOnFloor(Long defaultValue) {
        while (true) {
            try {
                if (mode == CommandMode.CLI_UserMode) {
                    String prompt = "Enter number of flats on floor";
                    if (defaultValue != null) prompt += " (space for " + defaultValue + ")";
                    System.out.print(prompt + " > ");
                }
                String input = scanner.nextLine().trim();
                long count;
                if (mode != CommandMode.CLI_UserMode && input.isEmpty() && defaultValue != null) {
                    count = defaultValue;
                } else {
                    count = Long.parseLong(input);
                }
                if (count <= 0) throw new IllegalArgumentException("Number of flats on floor must be greater than 0");
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

    private Integer readNumberOfLifts(Integer defaultValue) {
        while (true) {
            try {
                if (mode == CommandMode.CLI_UserMode) {
                    String prompt = "Enter number of lifts";
                    if (defaultValue != null) prompt += " (space for " + defaultValue + ")";
                    System.out.print(prompt + " > ");
                }
                String input = scanner.nextLine().trim();
                int count;
                if (mode != CommandMode.CLI_UserMode && input.isEmpty() && defaultValue != null) {
                    count = defaultValue;
                } else {
                    count = Integer.parseInt(input);
                }
                if (count <= 0) throw new IllegalArgumentException("Number of lifts must be greater than 0");
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
}