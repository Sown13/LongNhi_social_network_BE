package com.example.social_network.service.message.group_member;

import com.example.social_network.model.message.GroupMember;
import com.example.social_network.service.IGeneralService;

public interface IGroupMemberService extends IGeneralService<GroupMember> {

 void removeUserFromGroup(Long groupId,Long userId);
}
