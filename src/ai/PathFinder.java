package ai;

import main.GamePanel;

import java.util.ArrayList;

public class PathFinder {
    public ArrayList<Node> pathList = new ArrayList<>();
    GamePanel gamePanel;
    Node[][] nodes;
    ArrayList<Node> openList = new ArrayList<>();
    Node startNode, goalNode, currentNode;
    boolean goalReached;
    int step;

    public PathFinder(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        instantiateNode();
    }

    public void instantiateNode() {
        nodes = new Node[GamePanel.maxWorldCol][GamePanel.maxWorldRow];

        int col = 0;
        int row = 0;

        while (col < GamePanel.maxWorldCol && row < GamePanel.maxWorldRow) {
            nodes[col][row] = new Node(col, row);
            col++;
            if (col == GamePanel.maxWorldCol) {
                col = 0;
                row++;
            }
        }
    }

    public void resetNode() {
        int col = 0;
        int row = 0;
        while (col < GamePanel.maxWorldCol && row < GamePanel.maxWorldRow) {
            nodes[col][row].open = false;
            nodes[col][row].checked = false;
            nodes[col][row].solid = false;
            col++;
            if (col == GamePanel.maxWorldCol) {
                col = 0;
                row++;
            }
        }
        openList.clear();
        pathList.clear();
        goalReached = false;
        step = 0;
    }

    public void setNodes(int startCol, int startRow, int goalCol, int goalRow) {

        resetNode();

        startNode = nodes[startCol][startRow];
        currentNode = startNode;
        goalNode = nodes[goalCol][goalRow];
        openList.add(currentNode);

        int col = 0;
        int row = 0;
        while (col < GamePanel.maxWorldCol && row < GamePanel.maxWorldRow) {


            int tileNum = gamePanel.tileManager.mapTileNum[gamePanel.currentMap][col][row];
            if (gamePanel.tileManager.tiles[tileNum].collision) {
                nodes[col][row].solid = true;
            }
            for (int i = 0; i < gamePanel.interactiveTile[1].length; i++) {
                if (gamePanel.interactiveTile[gamePanel.currentMap][i] != null && gamePanel.interactiveTile[gamePanel.currentMap][i].destructible) {
                    int itCol = gamePanel.interactiveTile[gamePanel.currentMap][i].worldX / gamePanel.tileSize;
                    int itRow = gamePanel.interactiveTile[gamePanel.currentMap][i].worldY / gamePanel.tileSize;
                    nodes[itCol][itRow].solid = true;
                }
            }
            getCost(nodes[col][row]);
            col++;
            if (col == GamePanel.maxWorldCol) {
                col = 0;
                row++;
            }

        }
    }

    public void getCost(Node node) {
        int xDistance = Math.abs(node.col - startNode.col);
        int yDistance = Math.abs(node.row - startNode.row);
        node.gCost = xDistance + yDistance;

        xDistance = Math.abs(node.col - goalNode.col);
        yDistance = Math.abs(node.row - goalNode.row);
        node.hCost = xDistance + yDistance;

        node.fCost = node.gCost + node.hCost;
    }

    public boolean search() {
        while (!goalReached && step < 500) {

            int col = currentNode.col;
            int row = currentNode.row;

            currentNode.checked = true;
            openList.remove(currentNode);

            if (row - 1 >= 0) {
                openNode(nodes[col][row - 1]);
            }
            if (col - 1 >= 0) {
                openNode(nodes[col - 1][row]);
            }
            if (row + 1 < GamePanel.maxWorldRow) {
                openNode(nodes[col][row + 1]);
            }
            if (col + 1 < GamePanel.maxWorldCol) {
                openNode(nodes[col + 1][row]);
            }

            int bestNodeIndex = 0;
            int bestNodefCost = 999;
            for (int i = 0; i < openList.size(); i++) {
                if (openList.get(i).fCost < bestNodefCost) {
                    bestNodeIndex = i;
                    bestNodefCost = openList.get(i).fCost;
                } else if (openList.get(i).fCost == bestNodefCost) {
                    if (openList.get(i).gCost < openList.get(bestNodeIndex).gCost) {
                        bestNodeIndex = i;
                    }
                }
            }
            if (openList.isEmpty()) {
                break;
            }
            currentNode = openList.get(bestNodeIndex);
            if (currentNode == goalNode) {
                goalReached = true;
                trackThePath();
            }
            step++;
        }
        return goalReached;
    }

    public void trackThePath() {

        Node current = goalNode;
        while (current != startNode) {
            pathList.add(0, current);
            current = current.parent;
        }
    }

    public void openNode(Node node) {
        if (!node.open && !node.checked && !node.solid) {
            node.open = true;
            node.parent = currentNode;
            openList.add(node);
        }
    }
}