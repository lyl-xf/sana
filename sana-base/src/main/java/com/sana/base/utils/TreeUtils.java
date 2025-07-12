package com.sana.base.utils;



import com.sana.base.syshandle.exception.SanaException;
import com.sana.base.syshandle.tree.HandleTree;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author LON
 * @create 2025/7/12
 */
public class TreeUtils {
    /**
     * 根据pid，构建树节点
     */
    public static <T extends HandleTree<T>> List<T> build(List<T> treeNodes, Long pid) {
        // pid不能为空
        List<T> treeList = new ArrayList<>();

        if(pid!=null){
            for (T treeNode : treeNodes) {
                if (pid.equals(treeNode.getPid())) {
                    treeList.add(findChildren(treeNodes, treeNode));
                }
            }
        }else {
            throw new SanaException("pid不能为空");
        }


        return treeList;
    }

    /**
     * 查找子节点
     */
    private static <T extends HandleTree<T>> T findChildren(List<T> treeNodes, T rootNode) {
        for (T treeNode : treeNodes) {
            if (rootNode.getId().equals(treeNode.getPid())) {
                rootNode.getChildren().add(findChildren(treeNodes, treeNode));
            }
        }
        return rootNode;
    }

    /**
     * 构建树节点
     */
    public static <T extends HandleTree<T>> List<T> build(List<T> treeNodes) {
        List<T> result = new ArrayList<>();

        // list转map
        Map<Long, T> nodeMap = new LinkedHashMap<>(treeNodes.size());
        for (T treeNode : treeNodes) {
            nodeMap.put(treeNode.getId(), treeNode);
        }

        for (T node : nodeMap.values()) {
            T parent = nodeMap.get(node.getPid());
            if (parent != null && !(node.getId().equals(parent.getId()))) {
                parent.getChildren().add(node);
                continue;
            }

            result.add(node);
        }

        return result;
    }

}
