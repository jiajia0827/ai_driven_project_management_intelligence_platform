package org.example.ai_driven_project_management_intelligence_platform.service.impl;

import org.example.ai_driven_project_management_intelligence_platform.dto.CurrentUserResponseDTO;
import org.example.ai_driven_project_management_intelligence_platform.entity.SysMenu;
import org.example.ai_driven_project_management_intelligence_platform.mapper.SysMenuMapper;
import org.example.ai_driven_project_management_intelligence_platform.service.SysMenuService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 菜单服务实现
 */
@Service
public class SysMenuServiceImpl implements SysMenuService {

    private final SysMenuMapper sysMenuMapper;

    public SysMenuServiceImpl(SysMenuMapper sysMenuMapper) {
        this.sysMenuMapper = sysMenuMapper;
    }

    @Override
    public List<CurrentUserResponseDTO.MenuDTO> getMenuTreeByRoleId(Integer roleId) {
        List<SysMenu> menuList = sysMenuMapper.selectMenusByRoleId(roleId);
        Map<Integer, CurrentUserResponseDTO.MenuDTO> map = new HashMap<>();
        List<CurrentUserResponseDTO.MenuDTO> roots = new ArrayList<>();

        // 先转为 DTO 并建立映射
        for (SysMenu m : menuList) {
            CurrentUserResponseDTO.MenuDTO dto = new CurrentUserResponseDTO.MenuDTO();
            dto.setMenuId(m.getMenuId());
            dto.setMenuName(m.getMenuName());
            dto.setPath(m.getPath());
            dto.setParentId(m.getParentId());
            dto.setMenuType(m.getMenuType());
            dto.setSort(m.getSort());
            map.put(dto.getMenuId(), dto);
        }

        // 再根据 parentId 组装树
        for (CurrentUserResponseDTO.MenuDTO dto : map.values()) {
            if (dto.getParentId() == null || dto.getParentId() == 0) {
                roots.add(dto);
            } else {
                CurrentUserResponseDTO.MenuDTO parent = map.get(dto.getParentId());
                if (parent != null) {
                    parent.getChildren().add(dto);
                } else {
                    roots.add(dto);
                }
            }
        }

        // 按 sort 排序，保证层级顺序与约定一致
        roots.sort((a, b) -> Byte.compare(a.getSort() != null ? a.getSort() : 0, b.getSort() != null ? b.getSort() : 0));
        for (CurrentUserResponseDTO.MenuDTO dto : map.values()) {
            dto.getChildren().sort((a, b) -> Byte.compare(a.getSort() != null ? a.getSort() : 0, b.getSort() != null ? b.getSort() : 0));
        }

        return roots;
    }
}

